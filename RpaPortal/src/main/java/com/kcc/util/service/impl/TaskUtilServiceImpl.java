package com.kcc.util.service.impl;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.velocity.runtime.directive.Foreach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.CrawlRunVO;
import com.kcc.biz.model.CrawlScheduleVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.service.ICrawlRequestService;
import com.kcc.biz.service.ICrawlScheduleService;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.ICrawlUtilService;
import com.kcc.util.service.ITaskUtilService;
import com.kcc.words.ConstWord;

@Component("taskUtilService")
public class TaskUtilServiceImpl implements ITaskUtilService {
	private static final Logger logger = LoggerFactory.getLogger(TaskUtilServiceImpl.class);
	 
	@Resource(name="crawlScheduleService")
	private ICrawlScheduleService crawlScheduleService; 
	 
	@Resource(name="crawlUtilService")
	private ICrawlUtilService crawlUtilService;
	 
	@Resource(name="crawlRequestService")
	private ICrawlRequestService crawlRequestService;
	
	SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	 
	@Scheduled(cron = "0 * * * * *")
	public void callCrawlApi() {
		String curDate = yyyyMMddHHmm.format(new Date()).toString();
		CrawlScheduleVO inCrawlScheduleVO = new CrawlScheduleVO();
		inCrawlScheduleVO.setCallDate(curDate);
		
		List<CrawlScheduleVO> outListCrawlScheduleVO = new ArrayList<CrawlScheduleVO>();
		
		try {
			outListCrawlScheduleVO = crawlScheduleService.listCrawlScheduleMenu(inCrawlScheduleVO);
			for (CrawlScheduleVO crawlScheduleVO : outListCrawlScheduleVO) {
				logger.info("callMenuId : " + crawlScheduleVO.getMenuId());
				String status = "Progress";
				
				// RequestVO 입력
				CrawlRequestVO inCrawlRequestVO = new CrawlRequestVO();
				inCrawlRequestVO.setMenuId(crawlScheduleVO.getMenuId());
				inCrawlRequestVO.setEmpNo(ConstWord.RUN_CRAWL_ADMIN);
				inCrawlRequestVO.setAttId("");
				
				try {
					// 웹크롤링 요청 정보 생성
					crawlRequestService.createCrawlRequest(inCrawlRequestVO);
				} 
				catch (Exception e) {
					status = "CreateError";
					e.printStackTrace();
				}
								
				try {
					// CrawlRunVO 입력
					CrawlRunVO inCrawlRunVO = new CrawlRunVO();
					inCrawlRunVO.setMenuId(crawlScheduleVO.getMenuId());
					inCrawlRunVO.setEmpNo(ConstWord.RUN_CRAWL_ADMIN);
					inCrawlRunVO.setRequestNo(inCrawlRequestVO.getNewRequestNo());
					
					// 웹크롤링 서버에 과제 요청
					status = crawlUtilService.requestCrawl(inCrawlRunVO);
				} 
				catch (Exception e) {
					status = "RequestError";
					e.printStackTrace();
				}
				
				// 요청이 실패한 경우
				if ("Fail".equals(status) || "RequestError".equals(status)) {
					// RequestVO 입력
					inCrawlRequestVO.setRequestNo(inCrawlRequestVO.getNewRequestNo());
					inCrawlRequestVO.setErrorMsg("웹크롤링 서버 요청시 오류가 발생 하였습니다.");
					inCrawlRequestVO.setStatusCd("RA004003");
					
					try {
						// 웹크롤링 요청 정보 변경
						crawlRequestService.updateCrawlRequest(inCrawlRequestVO);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			logger.info("callCrawlApi : " + curDate);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
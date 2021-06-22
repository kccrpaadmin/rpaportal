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
import com.kcc.biz.model.BotScheduleVO;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.CrawlRunVO;
import com.kcc.biz.model.CrawlScheduleVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.service.IBotScheduleService;
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
	
	@Resource(name="crawlRequestService")
	private ICrawlRequestService crawlRequestService;
	
	@Resource(name="crawlUtilService")
	private ICrawlUtilService crawlUtilService;
	 
	@Resource(name="botScheduleService")
	private IBotScheduleService botScheduleService;
		
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
				
				// RequestVO 입력
				CrawlRequestVO inCrawlRequestVO = new CrawlRequestVO();
				inCrawlRequestVO.setMenuId(crawlScheduleVO.getMenuId());
				inCrawlRequestVO.setEmpNo(ConstWord.RUN_CRAWL_ADMIN);
				
				// 웹크롤링 수행 (진행중인 경우 수행 안함)
				CrawlRequestVO outCrawlRequestVO = new CrawlRequestVO();
				outCrawlRequestVO = crawlUtilService.requestCrawl(inCrawlRequestVO);
				
				// 오류 발생시
				if ("Fail".equals(outCrawlRequestVO.getRequestStatus())) {
					outCrawlRequestVO.setStatusCd("RA005003");
					outCrawlRequestVO.setErrorMsg("웹크롤링 요청시 오류가 발생 하였습니다.");
					
					try {
						// 웹크롤링 요청 정보 변경
						crawlRequestService.updateCrawlRequest(outCrawlRequestVO);
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
	
	@Scheduled(cron = "0 * * * * *")
	public void callBotApi() {
		String curDate = yyyyMMddHHmm.format(new Date()).toString();
		BotScheduleVO inBotScheduleVO = new BotScheduleVO();
		inBotScheduleVO.setCallDate(curDate);

		List<BotScheduleVO> outListBotScheduleVO = new ArrayList<BotScheduleVO>();
		
		try {
			outListBotScheduleVO = botScheduleService.listBotScheduleMenu(inBotScheduleVO);
			for (BotScheduleVO botScheduleVO : outListBotScheduleVO) {
				
			}
			
			logger.info("callBotApi : " + curDate);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
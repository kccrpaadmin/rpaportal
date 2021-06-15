package com.kcc.util.service.impl;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.CrawlRunVO;
import com.kcc.biz.service.ICrawlRequestService;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.ICrawlUtilService;
import com.kcc.words.ConstWord;

@Component("crawlUtilService")
public class CrawlUtilServiceImpl implements ICrawlUtilService {
	private static final Logger logger = LoggerFactory.getLogger(CrawlUtilServiceImpl.class);
	
	@Resource(name="commonUtilService")
	ICommonUtilService commonUtilService;
	
	@Resource(name="crawlRequestService")
	private ICrawlRequestService crawlRequestService;
	
	// 웹크롤링 요청 공통 메소드
	public String requestCrawl(CrawlRequestVO vo) {
		String status = "Fail";

		// RequestVO 출력
		CrawlRequestVO outCrawlRequestVO = new CrawlRequestVO();
		
		try {
			// 웹크롤링 메뉴별 진행여부 조회
			outCrawlRequestVO = crawlRequestService.getCrawlRequestStatus(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 진행여부를 판단 (진행중인 건이 있는 경우) - 정지
		if ("Progress".equals(outCrawlRequestVO.getRequestStatus())) {
			// 진행중으로 변경 (Progress)
			status = outCrawlRequestVO.getRequestStatus();
		}
		// 진행여부를 판단 (진행중인 건이 없는 경우) - 시작
		else {
			try {
				// 웹크롤링 요청 정보 생성
				crawlRequestService.createCrawlRequest(vo);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				// 웹크롤링 수행 모델
				CrawlRunVO crawlRunVO = new CrawlRunVO();   
				crawlRunVO.setMenuId(vo.getMenuId());
				crawlRunVO.setEmpNo(vo.getEmpNo());
				crawlRunVO.setRequestNo(vo.getNewRequestNo());
				
				// 로컬, 개발, 운영 분기
				if ("Real".equals(commonUtilService.getServerEnv())) {
					crawlRunVO.setApiUrl(ConstWord.CRAWL_REAL_IP);
					crawlRunVO.setApiKey(ConstWord.CRAWL_API_KEY);
				}
				else if ("Dev".equals(commonUtilService.getServerEnv())) {
					crawlRunVO.setApiUrl(ConstWord.CRAWL_DEV_IP);
					crawlRunVO.setApiKey(ConstWord.CRAWL_API_KEY);
				}
				else if ("Local2".equals(commonUtilService.getServerEnv())) {
					crawlRunVO.setApiUrl(ConstWord.CRAWL_LOCAL2_IP);
					crawlRunVO.setApiKey(ConstWord.CRAWL_API_KEY);
				}
				else {
					crawlRunVO.setApiUrl(ConstWord.CRAWL_LOCAL1_IP);
					crawlRunVO.setApiKey(ConstWord.CRAWL_API_KEY);
				}
				
				URI uri = new URI(crawlRunVO.getApiUrl());
				uri = new URIBuilder(uri)
						.addParameter("api", crawlRunVO.getApiKey())
						.addParameter("emp", crawlRunVO.getEmpNo())
						.addParameter("menu", crawlRunVO.getMenuId())
						.addParameter("request", crawlRunVO.getRequestNo())
						.build();
				
				logger.info(uri.toString());
				
				// HttpClient 생성
				HttpClient client = HttpClientBuilder.create().build(); 
				HttpGet getRequest = new HttpGet(uri);
				
				// HttpResponse 생성
				HttpResponse response = client.execute(getRequest);
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					String content = EntityUtils.toString(entity);
					
					// 요청이 성공한 경우
					if ("Success".equals(content)) {
						status = "Success";
					}
				}			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 요청이 실패한 경우
		if ("Fail".equals(status)) {
			// RequestVO 입력
			vo.setRequestNo(vo.getNewRequestNo());
			vo.setStatusCd("RA005003");
			vo.setErrorMsg("웹크롤링 서버 요청시 오류가 발생 하였습니다.");
			
			try {
				// 웹크롤링 요청 정보 변경
				crawlRequestService.updateCrawlRequest(vo);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return status;
	}
}
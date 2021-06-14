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
import com.kcc.biz.model.BotRequestVO;
import com.kcc.biz.model.BotRunVO;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.CrawlRunVO;
import com.kcc.biz.service.IBotRequestService;
import com.kcc.biz.service.ICrawlRequestService;
import com.kcc.util.service.IBotUtilService;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.words.ConstWord;

@Component("botUtilService")
public class BotUtilServiceImpl implements IBotUtilService {
	private static final Logger logger = LoggerFactory.getLogger(BotUtilServiceImpl.class);
	
	@Resource(name="commonUtilService")
	ICommonUtilService commonUtilService;
	
	@Resource(name="botRequestService")
	private IBotRequestService botRequestService;
	
	// 봇 요청 공통 메소드
	public String requestBot(BotRequestVO vo) {
		String status = "Fail";
		
		// RequestVO 출력
		BotRequestVO outBotRequestVO = new BotRequestVO();
		
		try {
			// 웹크롤링 메뉴별 진행여부 조회
			outBotRequestVO = botRequestService.getBotRequestStatus(vo);
		} 
		catch (Exception e) {
			status = "ListSearchError";
			e.printStackTrace();
		}
		
		// 진행여부를 판단
		if ("Stop".equals(outBotRequestVO.getRequestStatus())) {
			try {
				// 봇 요청 정보 생성
				botRequestService.createBotRequest(vo);
			} 
			catch (Exception e) {
				status = "CreateError";
				e.printStackTrace();
			}
		
			try {
				// 사용자에서 받은 정보를 바탕으로 저장
				// AttId 및 현장코드 및 기준년월 정보
				
			} 
			catch (Exception e) {
				status = "InputSaveError";
				e.printStackTrace();
			}
			
			try {
				// 봇 수행 모델
				BotRunVO botRunVO = new BotRunVO();   
				botRunVO.setMenuId(vo.getMenuId());
				botRunVO.setEmpNo(vo.getEmpNo());
				botRunVO.setRequestNo(vo.getNewRequestNo());
				
				// 로컬, 개발, 운영 분기
				if ("Real".equals(commonUtilService.getServerEnv())) {
					botRunVO.setApiUrl(ConstWord.UIPATH_REAL_IP);
					botRunVO.setApiKey(ConstWord.UIPATH_REAL_API_KEY);
				}
				else if ("Dev".equals(commonUtilService.getServerEnv())) {
					botRunVO.setApiUrl(ConstWord.UIPATH_DEV_IP);
					botRunVO.setApiKey(ConstWord.UIPATH_DEV_API_KEY);
				}
				
				// 토큰 키 발급
				
				
				// API 호출
				try {
					URI uri = new URI(botRunVO.getApiUrl());
					uri = new URIBuilder(uri)
							.addParameter("api", botRunVO.getApiKey())
							.addParameter("emp", botRunVO.getEmpNo())
							.addParameter("menu", botRunVO.getMenuId())
							.addParameter("request", botRunVO.getRequestNo())
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
			catch (Exception e) {
				status = "RequestError";
				e.printStackTrace();
			}
		}
		else {
			// Progress 상태로 변경
			status = outBotRequestVO.getRequestStatus();
		}
		
		// 요청이 실패한 경우
		if ("Fail".equals(status) || "TokenError".equals(status) || "RequestError".equals(status)) {
			// RequestVO 입력
			vo.setRequestNo(vo.getNewRequestNo());
			vo.setStatusCd("RA005003");
			vo.setErrorMsg("봇 서버 요청시 오류가 발생 하였습니다.");
			
			try {
				// 봇 요청 정보 변경
				botRequestService.updateBotRequest(vo);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return status;
	}	
}
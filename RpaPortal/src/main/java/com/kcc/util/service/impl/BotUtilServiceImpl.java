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
	public BotRequestVO requestBot(BotRequestVO vo) {
		// RequestVO 출력
		BotRequestVO outBotRequestVO = new BotRequestVO();
		outBotRequestVO.setRequestStatus("Fail");
		
		try {
			// 웹크롤링 메뉴별 진행여부 조회
			outBotRequestVO = botRequestService.getBotRequestStatus(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
			outBotRequestVO.setRequestStatus("Fail");
			return outBotRequestVO;
		}
		
		// 진행여부를 판단 (진행중인 건이 있는 경우) - 정지
		if ("Progress".equals(outBotRequestVO.getRequestStatus())) {
			// 진행중으로 변경 (Progress)
			return outBotRequestVO;
		}
		// 진행여부를 판단 (진행중인 건이 없는 경우) - 시작
		else {
			try {
				// 봇 요청 정보 생성
				botRequestService.createBotRequest(vo);
				
				// NewRequestNo 삽입
				vo.setRequestNo(vo.getNewRequestNo());
				outBotRequestVO.setRequestNo(vo.getNewRequestNo());
			} 
			catch (Exception e) {
				e.printStackTrace();
				outBotRequestVO.setRequestStatus("Fail");
				return outBotRequestVO;
			}
			
			try {
				// 봇 요청시 메뉴별 저장
				createByMenuData(vo);
			} 
			catch (Exception e) {
				e.printStackTrace();
				outBotRequestVO.setRequestStatus("Fail");
				return outBotRequestVO;
			}
			
			try {
				// 봇 수행 모델
				BotRunVO botRunVO = new BotRunVO();   
				botRunVO.setMenuId(vo.getMenuId());
				botRunVO.setEmpNo(vo.getEmpNo());
				botRunVO.setRequestNo(vo.getRequestNo());
				
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
				
			}
			catch (Exception e) {
				e.printStackTrace();
				outBotRequestVO.setRequestStatus("Fail");
				return outBotRequestVO;
			}
		}
		
		return outBotRequestVO;
	}
	
	// 
	public void createByMenuData(BotRequestVO vo) throws Exception {
		// 파라미터를 받는 메뉴ID를 선별적으로 처리
		if (vo.getMenuId().equals("RA004003") ) {
			// BotRequestVO에 스트링 하나를 선언해서 인풋 파라미터를 처리한다.
			
			
		}
		
	}
	
}
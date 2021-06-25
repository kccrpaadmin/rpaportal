package com.kcc.util.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
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
				botRunVO.setUserId(vo.getUserId());
				botRunVO.setRequestNo(vo.getRequestNo());
				
				// 로컬, 개발, 운영 분기
				if ("Real".equals(commonUtilService.getServerEnv())) {
					botRunVO.setApiUrl(ConstWord.ORCHESTRATOR_REAL_IP);
				}
				else if ("Dev".equals(commonUtilService.getServerEnv())) {
					botRunVO.setApiUrl(ConstWord.ORCHESTRATOR_DEV_IP);
				}
				else {
					botRunVO.setApiUrl(ConstWord.ORCHESTRATOR_DEV_IP);
				}
				
				logger.info(botRunVO.getApiUrl());
				
			    test(botRunVO);		
				
				
				/*
				 * //json data String jsonlnputString =
				 * "{'tenancyName': 'default','usernameOrEmailAddress': 'admin','password': 'const!2pri'}"
				 * ; //JSON ELE Output stream try(OutputStream os = conn.getOutputStream()) {
				 * byte[] input = jsonlnputString.getBytes("utf-8"); os.write(input, 0,
				 * input.length); } //Response data YEYE try(BufferedReader br = new
				 * BufferedReader( new InputStreamReader(conn.getInputStream(), "utf-8"))) {
				 * StringBuilder response = new StringBuilder(); String responseLine = null;
				 * while ((responseLine = br.readLine()) != null) {
				 * response.append(responseLine.trim()); }
				 * System.out.println(response.toString()); }
				 * 
				 * conn.disconnect();
				 */
								
				
				/*
				 * String urlStr = botRunVO.getApiUrl() + "/api/Account/Authenticate";
				 * 
				 * StringBuffer sb = new StringBuffer();
				 * 
				 * TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				 * public java.security.cert.X509Certificate[] getAcceptedIssuers() { return
				 * null; }
				 * 
				 * public void checkClientTrusted(X509Certificate[] certs, String authType) { }
				 * 
				 * public void checkServerTrusted(X509Certificate[] certs, String authType) { }
				 * } };
				 * 
				 * SSLContext sc = SSLContext.getInstance("SSL"); sc.init(null, trustAllCerts,
				 * new java.security.SecureRandom());
				 * HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				 * 
				 * URL url = new URL(urlStr); HttpURLConnection conn = (HttpURLConnection)
				 * url.openConnection(); conn.setRequestMethod("POST");
				 * conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
				 * conn.setDoOutput(true);
				 * 
				 * //json data String jsonlnputString =
				 * "{'tenancyName': 'default','usernameOrEmailAddress': 'admin','password': 'const!2pri'}"
				 * ; //JSON ELE Output stream try(OutputStream os = conn.getOutputStream()) {
				 * byte[] input = jsonlnputString.getBytes("utf-8"); os.write(input, 0,
				 * input.length); } //Response data YEYE try(BufferedReader br = new
				 * BufferedReader( new InputStreamReader(conn.getInputStream(), "utf-8"))) {
				 * StringBuilder response = new StringBuilder(); String responseLine = null;
				 * while ((responseLine = br.readLine()) != null) {
				 * response.append(responseLine.trim()); }
				 * System.out.println(response.toString()); }
				 * 
				 * conn.disconnect();
				 */
				
				
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
	
	public TrustManager[] createTrustManagers() throws Exception { 
		TrustManager[] trustAllCerts = new TrustManager[] {
			new X509TrustManager() { 
				public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) { } 
				public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) { } 
				public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
					return new java.security.cert.X509Certificate[]{}; 
				} 
			}
		};
		
		return trustAllCerts; 
	}
	
	public String test(BotRunVO botRunVO) throws Exception {
		String test = "";
		
		JSONObject inDataJsonObject = new JSONObject();
        inDataJsonObject.put("tenancyName", ConstWord.ORCHESTRATOR_TENANT_NM);
        inDataJsonObject.put("usernameOrEmailAddress", ConstWord.ORCHESTRATOR_USER_ID);
        inDataJsonObject.put("password", ConstWord.ORCHESTRATOR_USER_PWD);
        
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, createTrustManagers(), new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HostnameVerifier allHostsValid = (hostname, session) -> true; 
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		// HttpURLConnection 정의
		HttpURLConnection conn = null;
		
        // URL 설정
        URL url = new URL(botRunVO.getApiUrl() + "/api/Account/Authenticate");
        conn = (HttpURLConnection) url.openConnection();
        
        // Request 형식 설정
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        conn.setDoOutput(true);
        
        // Stream 준비
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        
        // Request에 쓰기
        bw.write(inDataJsonObject.toString());
        bw.flush();
        bw.close();
        
        // 결과값 요청
        int responseCode = conn.getResponseCode();
        
        // 성공인 경우
        if (responseCode == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
            	sb.append(line);
            }
            
            test = sb.toString();
            
            br.close();
            logger.info(test);
        }
        
        conn.disconnect();
	
		return test;
	}
	
	
		
	
}
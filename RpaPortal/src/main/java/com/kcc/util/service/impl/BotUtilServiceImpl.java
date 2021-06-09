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
import com.kcc.biz.model.BotRunVO;
import com.kcc.util.service.IBotUtilService;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.words.ConstWord;

@Component("botUtilService")
public class BotUtilServiceImpl implements IBotUtilService {
	private static final Logger logger = LoggerFactory.getLogger(BotUtilServiceImpl.class);
	
	@Resource(name="commonUtilService")
	ICommonUtilService commonUtilService;
	
	// 객체 비어 있는지 확인
	public String requestBot(BotRunVO vo) {
		String status = "Fail";
		
		// 로컬, 개발, 운영 분기
		if ("Real".equals(commonUtilService.getServerEnv())) {
			vo.setApiUrl(ConstWord.CRAWL_REAL_IP);
			vo.setApiKey(ConstWord.CRAWL_API_KEY);
		}
		else if ("Dev".equals(commonUtilService.getServerEnv())) {
			vo.setApiUrl(ConstWord.CRAWL_DEV_IP);
			vo.setApiKey(ConstWord.CRAWL_API_KEY);
		}
		else if ("Local2".equals(commonUtilService.getServerEnv())) {
			vo.setApiUrl(ConstWord.CRAWL_LOCAL2_IP);
			vo.setApiKey(ConstWord.CRAWL_API_KEY);
		}
		else {
			vo.setApiUrl(ConstWord.CRAWL_LOCAL1_IP);
			vo.setApiKey(ConstWord.CRAWL_API_KEY);
		}
		
		try {
			URI uri = new URI(vo.getApiUrl());
			uri = new URIBuilder(uri)
					.addParameter("api", vo.getApiKey())
					.addParameter("emp", vo.getEmpNo())
					.addParameter("menu", vo.getMenuId())
					.addParameter("request", vo.getRequestNo())
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
		catch (Exception e){
			e.printStackTrace();
		}
		
		return status;
	}	
}
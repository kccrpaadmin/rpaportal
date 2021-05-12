package com.kcc.auth;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import com.kcc.biz.service.IAccessService;
import com.kcc.util.service.ICommonUtilService;

import sun.misc.MessageUtils;

public class UseCustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(UseCustomAuthenticationSuccessHandler.class);
	
	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
    	logger.info("onAuthenticationSuccess");
    	
        // 디바이스 필터는 인터셉터 Url 패턴 구간에서만 동작 함 
    	// 디바이스 종류 및 접속 로그 저장 기능은 Home.do 이관
    	// 로그인, 홈 버튼 이동을 구분 후, 저장 
    	// Redirect 진행시 POST 방식으로 구현하는 예제
    	
    	FlashMap flashMap = new FlashMap();
    	flashMap.put("pParam", "Login");

    	if (!CollectionUtils.isEmpty(flashMap)) {
    		FlashMapManager flashMapManager = new SessionFlashMapManager();
    		flashMapManager.saveOutputFlashMap(flashMap, req, res);
    	}
		
        // CertInfo 정보 세션에 넣기 - UI 전역에서 사용
        HttpSession session = req.getSession();
        session.setAttribute("certInfo", commonUtilService.getCertInfo());
        
    	res.sendRedirect("/Main/Home.do");
    }
}
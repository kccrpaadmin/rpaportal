package com.kcc.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.ui.Model;

public class UseCustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(UseCustomAuthenticationFailureHandler.class);
	
	private String inputUserId;
    private String inputPwd;
    private String loginHandlingUrl;
    
	public String getInputUserId() {
		return inputUserId;
	}
	public String getInputPwd() {
		return inputPwd;
	}
	public String getLoginHandlingUrl() {
		return loginHandlingUrl;
	}
	public void setInputUserId(String inputUserId) {
		this.inputUserId = inputUserId;
	}
	public void setInputPwd(String inputPwd) {
		this.inputPwd = inputPwd;
	}
	public void setLoginHandlingUrl(String loginHandlingUrl) {
		this.loginHandlingUrl = loginHandlingUrl;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException, ServletException {
		logger.info("onAuthenticationFailure");
		
		String userId = req.getParameter(inputUserId);
        String pwd = req.getParameter(inputPwd);
        String errMsg = exception.getMessage();
        
        if (errMsg.equals("Maximum sessions of 1 for this principal exceeded")) {
        	errMsg = "MaxSessionError";
        }

        // 인증 오류 체크 (중복 로그인)  
        // 중복 로그인 경우도 해당 메소드 호출 인자 값이 없음.
    	req.getRequestDispatcher(loginHandlingUrl + errMsg).forward(req, res);
    	
    	/*
		logger.info(userId);
        logger.info(pwd);
        logger.info(errMsg);
        logger.info(loginHandlingUrl);
        
    	UseCustomAuthenticationProvider 오류 캐치도 가능
        if (exception instanceof UsernameNotFoundException) {
        	errMsg = "User Not Found";
        }
        else if (exception instanceof CredentialsExpiredException) {
        	errMsg = "Credentials Expired";
        }
        */
	}
}

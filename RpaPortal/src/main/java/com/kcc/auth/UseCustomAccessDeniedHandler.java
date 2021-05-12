package com.kcc.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UseCustomAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(UseCustomAccessDeniedHandler.class);
	  
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException ade) throws IOException, ServletException {
		logger.info("handle");
		
		/*
		logger.info("Exceiption : {}", ade);
		logger.info("LocalizedMessage : {}", ade.getLocalizedMessage());
		logger.info("Message : {}", ade.getMessage());
		logger.info("StackTrace : {}", ade.getStackTrace());
		*/
		
		/*
		forward 방식 url 변경 X
		getRequestDispatcher는 POST방식 url 사용 
		req.setAttribute("errMsg", ade.getMessage());
		req.getRequestDispatcher("/WEB-INF/views/user/denied.jsp").forward(req, res);
		*/

 		res.sendRedirect("/Common/AccessDenied.do");
	}
}
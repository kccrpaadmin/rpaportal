package com.kcc.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

public class UseCustomAuthenticationAjaxCertAndAuthFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(UseCustomAuthenticationAjaxCertAndAuthFilter.class);
	
	private String ajaxHeader;
    
	public String getAjaxHeader() {
		return ajaxHeader;
	}
	public void setAjaxHeader(String ajaxHeader) {
		this.ajaxHeader = ajaxHeader;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info("doFilter");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if (isAjaxRequest(req)) {
			try {
				chain.doFilter(req, res);
			}
			catch (AccessDeniedException e) {
				// 스프링 익셉션은 항상 403으로 발생 되서 인증객체에서 확인 후 처리 				
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth.getPrincipal().equals("anonymousUser")) {
					res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					logger.info("SC_UNAUTHORIZED : 401");
				}
				else {
					res.sendError(HttpServletResponse.SC_FORBIDDEN);
					logger.info("SC_FORBIDDEN : 403");
				}
			}
		} 
		else {
			chain.doFilter(req, res);
		}
	}

	private boolean isAjaxRequest(HttpServletRequest req) {
		boolean isTrue = false;
		
		if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
			isTrue = true;
		}
		
		return isTrue;
	}
	
	public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
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

import org.apache.tiles.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

public class UseCustomAuthenticationAjaxCertAndAuthFilter_New implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(UseCustomAuthenticationAjaxCertAndAuthFilter_New.class);
	
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
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String url = req.getServletPath().toString();
				logger.info(url);
				
				if (auth == null) {
					if (url.indexOf("/Work") > -1 || url.indexOf("/Admin") > -1) {
						logger.info("401");
						res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					}
					else {
						chain.doFilter(req, res);
					}
				}
				else {
					String roleNm = auth.getAuthorities().toString();
					if (url.indexOf("/Admin") > -1 && !"[ROLE_ADMIN]".equals(roleNm)) {
						logger.info("403");
						res.sendError(HttpServletResponse.SC_FORBIDDEN);
					}
					else {
						chain.doFilter(req, res);
					}
				}				
			}
			catch (Exception e) {
				logger.info("500");
				res.sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
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
		logger.info(req.getHeader("X-Requested-With"));
		return isTrue;
	}	
	
	public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
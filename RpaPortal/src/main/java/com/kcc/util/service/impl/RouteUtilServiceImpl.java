package com.kcc.util.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kcc.util.service.IRouteUtilService;

@Component("routeUtilService")
public class RouteUtilServiceImpl implements IRouteUtilService {
	private static final Logger logger = LoggerFactory.getLogger(RouteUtilServiceImpl.class);
		
	public void MessageAndMove(String pMsg, String pReturnPage) {
		try {
	        StringBuilder sb = new StringBuilder("/Common/MessageAndMove.do");
	        sb.append("?pMsg=" +  URLEncoder.encode(pMsg, "UTF-8"));
	        sb.append("&pReturnPage=" + URLEncoder.encode(pReturnPage, "UTF-8"));
	        
	        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
	        response.sendRedirect(sb.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
}
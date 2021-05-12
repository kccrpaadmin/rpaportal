package com.kcc.tiles;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kcc.auth.UseCustomUserDetails;

import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;

public class CustumViewPreparer implements ViewPreparer {
	private static final Logger logger = LoggerFactory.getLogger(CustumViewPreparer.class);
	
	@Override
	public void execute(Request context, AttributeContext attributeContext) throws PreparerException {
		logger.info("CustumViewPreparer");
		
		// 사용자 정보 조회
		UseCustomUserDetails userDetails = (UseCustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		// request 객체 및 세션을 얻는 방법
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		// 해더 시간 정보
		attributeContext.putAttribute("curTime", new Attribute(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())), true);
		
		// 해더 사용자 정보
		attributeContext.putAttribute("userInfo", new Attribute(userDetails.getUserNm() + " " + userDetails.getDutyNm() + " " + userDetails.getDeptNm()), true);
		
		// 전역 사용자 정보
		attributeContext.putAttribute("certInfo", new Attribute(session.getAttribute("certInfo")), true);
	}
}
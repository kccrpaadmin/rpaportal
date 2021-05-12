package com.kcc.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kcc.biz.model.LoginVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IUserService;
import com.kcc.util.service.ICommonUtilService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UseCustomAuthenticationProvider implements AuthenticationProvider {
	private static final Logger logger = LoggerFactory.getLogger(UseCustomAuthenticationProvider.class);

	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@Resource(name="loginService")
	private ILoginService loginService;
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("authenticate");
		
		// security-context.xml 정의된 username-parameter, password-parameter 값 
		String userId = (String)authentication.getPrincipal();
        String pwd = (String)authentication.getCredentials(); 
        // 암호가 암호화된 경우와 일반적인 경우
        String encPwd = (pwd.length() < 20 ? commonUtilService.getSHA256(pwd) : pwd);

        // LoginVO 입력 정보
        LoginVO inLoginVO = new  LoginVO();
        inLoginVO.setUserId(userId);
        inLoginVO.setPwd(encPwd);
        
        // LoginVO 출력 정보
        LoginVO outLoginVO = new  LoginVO();
        try {
			outLoginVO = loginService.getLoginInfo(inLoginVO);
		} 
        catch (Exception e) {
			e.printStackTrace();
		} 
		
        // 암호가 틀린 경우 (오류 유도)
		if (outLoginVO.getLoginCd().equals("PwdError")) {
			throw new BadCredentialsException("PwdError");
		}
		// 아이디가 틀린 경우 (오류 유도)
		else if (outLoginVO.getLoginCd().equals("UserIdError")) {
			throw new UsernameNotFoundException("UserIdError");
		}
		
		// UserVO 입력 정보
		UserVO inUserVO = new  UserVO();
		inUserVO.setUserId(userId);
				
		// UserVO 출력 정보
		UserVO outUserVO = new  UserVO();
        try {
        	outUserVO = userService.getUserInfo(inUserVO);
		} 
        catch (Exception e) {
			e.printStackTrace();
		} 
        
        // 사용자 정보 저장
        UseCustomUserDetails useCustomUserDetails= new UseCustomUserDetails();
        useCustomUserDetails.setRoleType(outUserVO.getRoleType());
        useCustomUserDetails.setUserId(outUserVO.getUserId());
        useCustomUserDetails.setPwd(outUserVO.getPwd());
        useCustomUserDetails.setUserNm(outUserVO.getUserNm());
        useCustomUserDetails.setEmail(outUserVO.getEmail());
        useCustomUserDetails.setDeptCd(outUserVO.getDeptCd());
        useCustomUserDetails.setDeptNm(outUserVO.getDeptNm());
        useCustomUserDetails.setTitleCd(outUserVO.getTitleCd());
        useCustomUserDetails.setTitleNm(outUserVO.getTitleNm());
        useCustomUserDetails.setDutyCd(outUserVO.getDutyCd());
        useCustomUserDetails.setDutyNm(outUserVO.getDutyNm());
        useCustomUserDetails.setEmpNo(outUserVO.getEmpNo());
        useCustomUserDetails.setCompTel(outUserVO.getCompTel());
        useCustomUserDetails.setCompAddr(outUserVO.getCompAddr());
        useCustomUserDetails.setTel(outUserVO.getTel());
        useCustomUserDetails.setMobile(outUserVO.getMobile());
        useCustomUserDetails.setAddr(outUserVO.getAddr());
        useCustomUserDetails.setUserSignUrl(outUserVO.getUserSignUrl());
        useCustomUserDetails.setUserPhotoUrl(outUserVO.getUserPhotoUrl());
        useCustomUserDetails.setJobNm(outUserVO.getJobNm());
        useCustomUserDetails.setDbServer(outUserVO.getDbServer());

        // 권한 설정
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(outUserVO.getRoleType()));
        
        // 토큰 저장
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId, pwd, roles);
        token.setDetails(useCustomUserDetails);
        
        // IP 체크 가능
        WebAuthenticationDetails webAuthDetails = (WebAuthenticationDetails) authentication.getDetails();
        webAuthDetails.getRemoteAddress();
        
        // 토큰 결과 리턴
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}	
}

package com.kcc.controller.common;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kcc.biz.model.AccessVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/Account")
@Controller
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@Resource(name="routeUtilService")
	private IRouteUtilService routeUtilService;

	@Resource(name="accessService")
	private IAccessService accessService;
	
	@RequestMapping(value = "/Login.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String Login(HttpServletRequest req, HttpServletResponse res) {
		logger.info("/Account/Login.do");
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(req, res, auth);
		}
		
		return "Account/Login";
	}
	
	@RequestMapping(value = "/LoginHandling.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void LoginHandling(@RequestParam(value="pParam", required=false) String pParam, HttpServletRequest req, HttpServletResponse res) {
		logger.info("/Account/LoginHandling.do");
		
		try {
			// 로그아웃이 아닌 경우
			if (!pParam.equals("Logout")) {
				AccessVO accessVO = new AccessVO();
				accessVO.setAccessUrl(req.getRequestURI());
				accessVO.setAccessIp(commonUtilService.getClientIp(req));
				accessVO.setAccessDevice(commonUtilService.getDeviceType(req));
				accessVO.setUserId(req.getParameter("UserId"));
				accessVO.setPwd(req.getParameter("Pwd"));
				
				// 로그인 실패 로그 저장 
				accessService.createAccessFail(accessVO);
			}
			
			if (pParam.equals("AuthError")) {
				routeUtilService.MessageAndMove("인증이 필요 합니다.", "/Account/Login.do");
			}
			else if (pParam.equals("UserIdError")) {
				routeUtilService.MessageAndMove("아이디가 올바르지 않습니다.", "/Account/Login.do");
			}
			else if (pParam.equals("PwdError")) {
				routeUtilService.MessageAndMove("비밀번호가 일치하지 않습니다.", "/Account/Login.do");
			}
			else if (pParam.equals("MaxSessionError")) {
				routeUtilService.MessageAndMove("이미 로그인 되어 있습니다.", "/Account/Login.do");
			}
			else if (pParam.equals("Logout")) {
				routeUtilService.MessageAndMove("로그아웃 되었습니다.", "/Account/Login.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

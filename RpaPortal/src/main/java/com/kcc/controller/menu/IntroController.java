package com.kcc.controller.menu;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/Intro")
@Controller
public class IntroController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(IntroController.class);
	
	@GetMapping("/Rpa.do")
	public String Rpa(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Intro/Rpa.do");
		return "Intro/Rpa";
	}
	
	@GetMapping("/Kpa.do")
	public String Kpa(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Intro/Kpa.do");
		return "Intro/Kpa";
	}
}

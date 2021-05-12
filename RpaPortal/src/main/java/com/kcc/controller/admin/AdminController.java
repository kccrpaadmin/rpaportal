package com.kcc.controller.admin;

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
import com.kcc.biz.service.ILoginService;
import com.kcc.controller.base.BaseController;

@RequestMapping("/Admin")
@Controller
public class AdminController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@GetMapping("/Admin.do")
	public String Admin(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Admin/Admin.do");
		
		return "Admin/Admin";
	}	
}

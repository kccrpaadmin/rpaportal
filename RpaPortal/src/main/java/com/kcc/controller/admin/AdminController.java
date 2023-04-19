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
import com.kcc.util.service.ICommonUtilService;

@RequestMapping("/Admin")
@Controller
public class AdminController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@GetMapping("/Admin.do")
	public String Admin(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Admin/Admin.do");
		
		return "redirect:/Admin/AnalysisManage.do";
	}
	
	@GetMapping("/CodeManage.do")
	public String CodeManage(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Admin/CodeManage.do");
		
		return "Admin/CodeManage";
	}
	
	@GetMapping("/MenuManage.do")
	public String MenuManage(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Admin/MenuManage.do");
		
		model.addAttribute("taskTypeComboCd", commonUtilService.getGridCodeCombo("RA004", "Cd"));
		model.addAttribute("taskTypeComboCdNm", commonUtilService.getGridCodeCombo("RA004", "CdNm"));
		model.addAttribute("execTypeComboCd", commonUtilService.getGridCodeCombo("RA007", "Cd"));
		model.addAttribute("execTypeComboCdNm", commonUtilService.getGridCodeCombo("RA007", "CdNm"));
		model.addAttribute("timeTypeComboCd", commonUtilService.getGridCodeCombo("RA003", "Cd"));
		model.addAttribute("timeTypeComboCdNm", commonUtilService.getGridCodeCombo("RA003", "CdNm"));
		
		return "Admin/MenuManage";
	}
	
	@GetMapping("/AuthManage.do")
	public String AuthManage(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Admin/AuthManage.do");
		
		return "Admin/AuthManage";
	}
	
	@GetMapping("/AnalysisManage.do")
	public String AnalysisManage(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Admin/AnalysisManage.do");
		
		model.addAttribute("beginDate", commonUtilService.getDateUserFormat("yyyy-MM-dd", "Month", -1));
		model.addAttribute("endDate", commonUtilService.getDateUserFormat("yyyy-MM-dd", "Month", 0));
		return "Admin/AnalysisManage";
	}
	
	@GetMapping("/AnalysisResult.do")
	public String AnalysisResult(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Admin/AnalysisResult.do");
		
		model.addAttribute("beginDate", commonUtilService.getDateUserFormat("yyyy-MM-dd", "Month", -1));
		model.addAttribute("endDate", commonUtilService.getDateUserFormat("yyyy-MM-dd", "Month", 0));
		return "Admin/AnalysisResult";
	}
	
	
}

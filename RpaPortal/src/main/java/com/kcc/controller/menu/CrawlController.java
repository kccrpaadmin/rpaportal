package com.kcc.controller.menu;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.jasypt.commons.CommonUtils;
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
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/Crawl")
@Controller
public class CrawlController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CrawlController.class);
	
	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	@GetMapping("/ListMenu.do")
	public String ListMenu() {
		logger.info("/Crawl/ListMenu.do");
		return "Crawl/ListMenu";
	}
	
	@GetMapping("/SystemCheckRun.do")
	public String SystemCheckRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Crawl/SystemCheckRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getCrawlMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Crawl/SystemCheckRun";
	}
	
	@GetMapping("/G2bRun.do")
	public String G2bRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Crawl/G2bRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getCrawlMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Crawl/G2bRun";
	}
	
	@GetMapping("/G2bManage.do")
	public String G2bManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Crawl/G2bManage.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getCrawlMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		model.addAttribute("startDate", commonUtilService.getDateUserFormat("yyyy-MM-dd", "Month", -1));
		model.addAttribute("endDate", commonUtilService.getDateUserFormat("yyyy-MM-dd", "Month", 0));
		
		return "Crawl/G2bManage";
	}
	
	@GetMapping("/KomisRun.do")
	public String KomisRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Crawl/KomisRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getCrawlMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Crawl/KomisRun";
	}
	
	@GetMapping("/KomisManage.do")
	public String KomisManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Crawl/KomisManage.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getCrawlMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		model.addAttribute("basicDate", commonUtilService.getDateUserFormat("yyyy-MM", "Month", 0));
		
		return "Crawl/KomisManage";
	}

	@GetMapping("/PersonInfoRun.do")
	public String PersonInfoRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Crawl/PersonInfoRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getCrawlMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Crawl/PersonInfoRun";
	}
	
	@GetMapping("/PersonInfoManage.do")
	public String PersonInfoManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Crawl/PersonInfoManage.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getCrawlMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		model.addAttribute("orgComboBox", commonUtilService.getCodeProcedureSelectBox("combo_org", "PRA_Crawl_listCrawlPersonInfoOrgCombo", "", true, "전체", "전체"));
		
		return "Crawl/PersonInfoManage";
	}
}

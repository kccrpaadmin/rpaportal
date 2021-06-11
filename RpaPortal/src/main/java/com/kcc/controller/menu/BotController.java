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

@RequestMapping("/Bot")
@Controller
public class BotController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BotController.class);
	
	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	@GetMapping("/ListMenu.do")
	public String ListMenu() {
		logger.info("/Bot/ListMenu.do");
		return "Bot/ListMenu";
	}
	
	@GetMapping("/MoneySendDocRun.do")
	public String MoneySendDocRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/MoneySendDocRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getBotMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Bot/MoneySendDocRun";
	}
	
	@GetMapping("/MoneySendDocManage.do")
	public String MoneySendDocManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/MoneySendDocManage.do");
		
		return "Bot/MoneySendDocManage";
	}
	
	@GetMapping("/InvoiceSlipCheckRun.do")
	public String InvoiceSlipCheckRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/InvoiceSlipCheckRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getBotMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Bot/InvoiceSlipCheckRun";
	}
	
	@GetMapping("/InvoiceSlipCheckManage.do")
	public String InvoiceSlipCheckManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/InvoiceSlipCheckManage.do");
		
		return "Bot/InvoiceSlipCheckManage";
	}
	
	@GetMapping("/CostDivideReviewRun.do")
	public String CostDivideReviewRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/CostDivideReviewRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getBotMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Bot/CostDivideReviewRun";
	}
	
	@GetMapping("/CostDivideReviewManage.do")
	public String CostDivideReviewManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/CostDivideReviewManage.do");
		
		return "Bot/CostDivideReviewManage";
	}
	
	@GetMapping("/EtcTaxCdSaveRun.do")
	public String EtcTaxCdSaveRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/EtcTaxCdSaveRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getBotMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Bot/EtcTaxCdSaveRun";
	}
	
	@GetMapping("/EtcTaxCdSaveManage.do")
	public String EtcTaxCdSaveManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/EtcTaxCdSaveManage.do");
		
		return "Bot/EtcTaxCdSaveManage";
	}
	
	@GetMapping("/KisconConstRun.do")
	public String KisconConstRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/KisconConstRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getBotMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Bot/KisconConstRun";
	}
	
	@GetMapping("/KisconConstManage.do")
	public String KisconConstManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/KisconConstManage.do");
		
		return "Bot/KisconConstManage";
	}
	
	@GetMapping("/PersonAppointRun.do")
	public String PersonAppointRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/PersonAppointRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getBotMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Bot/PersonAppointRun";
	}
	
	@GetMapping("/PersonAppointManage.do")
	public String PersonAppointManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/PersonAppointManage.do");
		
		return "Bot/PersonAppointManage";
	}
	
	@GetMapping("/EngineerRun.do")
	public String EngineerRun(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/EngineerRun.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getBotMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "Bot/EngineerRun";
	}
	
	@GetMapping("/EngineerManage.do")
	public String EngineerManage(String pMenuId, String pEmpNo, Model model) {
		logger.info("/Bot/EngineerManage.do");
		
		return "Bot/EngineerManage";
	}
}

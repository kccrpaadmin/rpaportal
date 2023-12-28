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
	
	@GetMapping("/MoneySendRun.do")
	public String MoneySendRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/MoneySendRun.do");
		
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
		
		return "Bot/MoneySendRun";
	}
	
	@GetMapping("/MoneySendManage.do")
	public String MoneySendManage(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/MoneySendManage.do");
		
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
		
		return "Bot/MoneySendManage";
	}
	
	@GetMapping("/EseroRun.do")
	public String EseroRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/EseroRun.do");
		
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
		
		return "Bot/EseroRun";
	}
	
	@GetMapping("/EseroManage.do")
	public String EseroManage(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/EseroManage.do");
		
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
		model.addAttribute("basicDate", commonUtilService.getDateUserFormat("yyyy-MM", "Month", -1));
		
		return "Bot/EseroManage";
	}
	
	@GetMapping("/CostDivideRun.do")
	public String CostDivideRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/CostDivideRun.do");
		
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
		model.addAttribute("targetSiteCdComboBox", commonUtilService.getCodeProcedureSelectBox("target_site_cd", "PRA_Bot_listBotCostDivideTargetSiteCdCombo", pUserId, true, "", ""));
		model.addAttribute("targetYearComboBox", commonUtilService.getCodeProcedureSelectBox("target_year", "PRA_Bot_listBotCostDivideTargetYearCombo", "", true, "", ""));
		model.addAttribute("targetQuarterComboBox", commonUtilService.getCodeProcedureSelectBox("target_quarter", "PRA_Bot_listBotCostDivideTargetQuarterCombo", "", true, "", ""));
		
		return "Bot/CostDivideRun";
	}
		
	@GetMapping("/EtcTaxRun.do")
	public String EtcTaxRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/EtcTaxRun.do");
		
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
		
		return "Bot/EtcTaxRun";
	}
		
	@GetMapping("/KisconConstRun.do")
	public String KisconConstRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
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
	public String KisconConstManage(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/KisconConstManage.do");
		
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
		
		return "Bot/KisconConstManage";
	}
	
	@GetMapping("/PersonAppointRun.do")
	public String PersonAppointRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
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
	public String PersonAppointManage(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/PersonAppointManage.do");
		
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
		
		return "Bot/PersonAppointManage";
	}
	
	@GetMapping("/EngineerRun.do")
	public String EngineerRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
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
	public String EngineerManage(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/EngineerManage.do");
		
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
		model.addAttribute("orgTypeComboBox", commonUtilService.getCodeProcedureSelectBox("org_type_cd", "PRA_Bot_listOrgTypeCombo", "", true, "전체", "전체"));
		
		return "Bot/EngineerManage";
	}
	
	@GetMapping("/EngineerEduRun.do")
	public String EngineerEduRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/EngineerEduRun.do");
		
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
		
		return "Bot/EngineerEduRun";
	}
	
	@GetMapping("/EngineerEduManage.do")
	public String EngineerEduManage(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/EngineerEduManage.do");
		
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
		model.addAttribute("orgTypeComboBox", commonUtilService.getCodeProcedureSelectBox("org_type_cd", "PRA_Bot_listOrgTypeCombo", "", true, "전체", "전체"));
		
		return "Bot/EngineerEduManage";
	}
	
	@GetMapping("/BidChangeRun.do")
	public String BidChangeRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/BidChangeRun.do");
		
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
		
		return "Bot/BidChangeRun";
	}
	
	@GetMapping("/PersonalBccRun.do")
	public String PersonalBccRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/PersonalBccRun.do");
		
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
		
		return "Bot/PersonalBccRun";
	}
	
	@GetMapping("/SalesEseroRun.do")
	public String SalesEseroRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/SalesEseroRun.do");
		
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
		
		return "Bot/SalesEseroRun";
	}
	
	@GetMapping("/SalesEseroManage.do")
	public String SalesEseroManage(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/SalesEseroManage.do");
		
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
		model.addAttribute("basicDate", commonUtilService.getDateUserFormat("yyyy-MM", "Month", -1));
		
		return "Bot/SalesEseroManage";
	}
	
	@GetMapping("/GlassPersonAppointRun.do")
	public String GlassPersonAppointRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/GlassPersonAppointRun.do");
		
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
	
	@GetMapping("/SCSystemCheckRun.do")
	public String SCSystemCheckRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/SCSystemCheckRun.do");
		
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
		
		return "Bot/SCSystemCheckRun";
	}	

	@GetMapping("/AdDailyReportRun.do")
	public String AdDailyReportRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/AdDailyReport.do");
		
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
		
		return "Bot/AdDailyReportRun";
	}
	
	@GetMapping("/AdDailyReportManage.do")
	public String AdDailyReportManage(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/AdDailyReportManage.do");
		
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
		model.addAttribute("select1ComboBox", commonUtilService.getCodeProcedureSelectBox("ad_select_1", "PRA_Bot_listAdCombo", "", false, "", ""));
		model.addAttribute("select2ComboBox", commonUtilService.getCodeProcedureSelectBox("ad_select_2", "PRA_Bot_listAdCombo", "", true, "", ""));
		
		return "Bot/AdDailyReportManage";
	}
	
	@GetMapping("/EaisRun.do")
	public String EaisRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/EaisRun.do");
		
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
		
		return "Bot/EaisRun";
	}
	
	@GetMapping("/DelayedPaymentRun.do")
	public String DelayedPaymentRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/EaisRun.do");
		
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
		
		return "Bot/DelayedPaymentRun";
	}
	
	@GetMapping("/DelayedPaymentManage.do")
	public String DelayedPaymentManage(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/DelayedPaymentManage.do");
		
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
		//model.addAttribute("select1ComboBox", commonUtilService.getCodeProcedureSelectBox("ad_select_1", "PRA_Bot_listAdCombo", "", false, "", ""));
		//model.addAttribute("select2ComboBox", commonUtilService.getCodeProcedureSelectBox("ad_select_2", "PRA_Bot_listAdCombo", "", true, "", ""));
		
		return "Bot/DelayedPaymentManage";
	}
	
	@GetMapping("/InsuranceRun.do")
	public String InsuranceRun(String pMenuId, String pEmpNo, String pUserId, Model model) {
		logger.info("/Bot/InsuranceRun.do");
		
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
		
		return "Bot/InsuranceRun";
	}
}

package com.kcc.controller.modal;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.CrawlSystemCheckVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.ICrawlSystemCheckService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/ModalBot")
@Controller
public class ModalBotController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ModalBotController.class);
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	@GetMapping("/WorkInfo.do")
	public String WorkInfo(String pMenuId, String pEmpNo, Model model) {
		logger.info("/ModalBot/WorkInfo.do");
		
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
		
		return "ModalBot/WorkInfo";
	}
	
	@GetMapping("/Schedule.do")
	public String Schedule(String pMenuId, Model model) {
		logger.info("/ModalBot/Schedule.do");
		
		model.addAttribute("menuId", pMenuId);
		
		return "ModalBot/Schedule";
	}
	
	@GetMapping("/EseroTargetDate.do")
	public String EseroTargetDate(String pMenuId, Model model) {
		logger.info("/ModalBot/EseroTargetDate.do");
		
		model.addAttribute("menuId", pMenuId);
		
		return "ModalBot/EseroTargetDate";
	}
	
	@GetMapping("/EseroRunResult.do")
	public String EseroRunResult(String pMenuId, String pRequestNo, Model model) {
		logger.info("/ModalBot/EseroRunResult.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("requestNo", pRequestNo);
		
		return "ModalBot/EseroRunResult";
	}
	
	@GetMapping("/EseroManageOraCheckList.do")
	public String EseroManageOraCheckList(String pMenuId, String pYearMon, Model model) {
		logger.info("/ModalBot/EseroManageOraCheckList.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("yearMon", pYearMon);
		
		return "ModalBot/EseroManageOraCheckList";
	}
	
	@GetMapping("/MoneySendRunResult.do")
	public String MoneySendRunResult(String pMenuId, String pRequestNo, Model model) {
		logger.info("/ModalBot/MoneySendRunResult.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("requestNo", pRequestNo);
		
		return "ModalBot/MoneySendRunResult";
	}
	
	@GetMapping("/CostDivideRunResult.do")
	public String CostDivideRunResult(String pMenuId, String pRequestNo, Model model) {
		logger.info("/ModalBot/CostDivideRunResult.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("requestNo", pRequestNo);
		
		return "ModalBot/CostDivideRunResult";
	}
	
	@GetMapping("/KisconConstRunResult.do")
	public String KisconConstRunResult(String pMenuId, String pRequestNo, Model model) {
		logger.info("/ModalBot/KisconConstRunResult.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("requestNo", pRequestNo);
		
		return "ModalBot/KisconConstRunResult";
	}
	
	@GetMapping("/EtcTaxRunResult.do")
	public String EtcTaxRunResult(String pMenuId, String pRequestNo, Model model) {
		logger.info("/ModalBot/EtcTaxRunResult.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("requestNo", pRequestNo);
		
		return "ModalBot/EtcTaxRunResult";
	}
	
	@GetMapping("/MoneySendManageSearchVendor.do")
	public String MoneySendManageSearchVendor(Model model) {
		logger.info("/ModalBot/MoneySendManageSearchVendor.do");
				
		return "ModalBot/MoneySendManageSearchVendor";
	}
	
	@GetMapping("/MoneySendManageSendAmt.do")
	public String MoneySendManageSendAmt(String pCheckId, Model model) {
		logger.info("/ModalBot/MoneySendManageSendAmt.do");
		
		model.addAttribute("checkId", pCheckId);
		
		return "ModalBot/MoneySendManageSendAmt";
	}
	
	@GetMapping("/PersonAppointRunResult.do")
	public String PersonAppointRunResult(String pMenuId, String pRequestNo, Model model) {
		logger.info("/ModalBot/PersonAppointRunResult.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("requestNo", pRequestNo);
		
		return "ModalBot/PersonAppointRunResult";
	}
}

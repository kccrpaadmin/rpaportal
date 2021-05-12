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
import com.kcc.biz.model.OcrRequestVO;
import com.kcc.biz.model.CrawlSystemCheckVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.ICrawlSystemCheckService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/ModalOcr")
@Controller
public class ModalOcrController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ModalOcrController.class);
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	@GetMapping("/WorkInfo.do")
	public String WorkInfo(String pMenuId, String pEmpNo, Model model) {
		logger.info("/ModalOcr/WorkInfo.do");
		
		// MenuVO 입력
		MenuVO inMenuVO = new MenuVO();
		inMenuVO.setMenuId(pMenuId);
		inMenuVO.setEmpNo(pEmpNo);
		
		// MenuVO 출력
		MenuVO outMenuVO = new MenuVO();
		
		try {
			// 메뉴 정보 상세 조회
			outMenuVO = menuService.getOcrMenu(inMenuVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outMenuVO", outMenuVO);
		
		return "ModalOcr/WorkInfo";
	}
	
	@GetMapping("/TextRunResult.do")
	public String TextRunResult(String pMenuId, String pRequestNo, Model model) {
		logger.info("/ModalOcr/TextRunResult.do");
		
		// OcrRequestVO 입력
		OcrRequestVO ocrRequestVO = new OcrRequestVO();
		ocrRequestVO.setMenuId(pMenuId);
		ocrRequestVO.setRequestNo(pRequestNo);
		model.addAttribute("ocrRequestVO", ocrRequestVO);
		
		return "ModalOcr/TextRunResult";
	}
	
	@GetMapping("/BusinessLicenseResult.do")
	public String BusinessLicenseResult(String pMenuId, String pRequestNo, Model model) {
		logger.info("/ModalOcr/BusinessLicenseResult.do");
		
		// OcrRequestVO 입력
		OcrRequestVO ocrRequestVO = new OcrRequestVO();
		ocrRequestVO.setMenuId(pMenuId);
		ocrRequestVO.setRequestNo(pRequestNo);
		model.addAttribute("ocrRequestVO", ocrRequestVO);
		
		return "ModalOcr/BusinessLicenseResult";
	}
}

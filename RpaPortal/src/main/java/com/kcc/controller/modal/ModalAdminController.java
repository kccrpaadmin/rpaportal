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
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;
import com.kcc.util.service.ICommonUtilService;

@RequestMapping("/ModalAdmin")
@Controller
public class ModalAdminController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ModalAdminController.class);
	
	@Resource(name="menuService")
	private IMenuService menuService;
		
	@GetMapping("/AuthManageAddUser.do")
	public String AuthManageAddUser(String pMenuId, String pMenuType, Model model) {
		logger.info("/ModalAdmin/AuthManageAddUser.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("menuType", pMenuType);
		
		return "ModalAdmin/AuthManageAddUser";
	}	
	
	@GetMapping("/AuthManageAddDegree.do")
	public String AuthManageAddDegree(String pMenuId, String pMenuType, Model model) {
		logger.info("/ModalAdmin/AuthManageAddDegree.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("menuType", pMenuType);
		
		return "ModalAdmin/AuthManageAddDegree";
	}	
	
	@GetMapping("/AuthManageAddDept.do")
	public String AuthManageAddDept(String pMenuId, String pMenuType, Model model) {
		logger.info("/ModalAdmin/AuthManageAddDept.do");
		
		model.addAttribute("menuId", pMenuId);
		model.addAttribute("menuType", pMenuType);
		
		return "ModalAdmin/AuthManageAddDept";
	}	
}

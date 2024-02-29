package com.kcc.controller.ajax;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

@RequestMapping("/AjaxMenu")
@Controller
public class AjaxMenuController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxMenuController.class);
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	@PostMapping("/ListCrawlMenu.do")
	public @ResponseBody List<MenuVO> ListCrawlMenu(@RequestBody MenuVO vo) {
		logger.info("/AjaxMenu/ListCrawlMenu.do");
		
		List<MenuVO> listMenuVO = new ArrayList<MenuVO>();
		try {
			listMenuVO = menuService.listCrawlMenu(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listMenuVO;
	}		
	
	@PostMapping("/ListOcrMenu.do")
	public @ResponseBody List<MenuVO> ListOcrMenu(@RequestBody MenuVO vo) {
		logger.info("/AjaxMenu/ListOcrMenu.do");
		
		List<MenuVO> listMenuVO = new ArrayList<MenuVO>();
		try {
			listMenuVO = menuService.listOcrMenu(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listMenuVO;
	}
	
	@PostMapping("/ListBotMenu.do")
	public @ResponseBody List<MenuVO> ListBotMenu(@RequestBody MenuVO vo) {
		logger.info("/AjaxMenu/ListBotMenu.do");
		logger.info("MenuVo.CategoryCd : " + vo.getCategoryCd());
		
		List<MenuVO> listMenuVO = new ArrayList<MenuVO>();
		try {
			listMenuVO = menuService.listBotMenu(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listMenuVO;
	}
	
	@PostMapping("/GetMenuAuth.do")
	public @ResponseBody MenuVO GetMenuAuth(@RequestBody MenuVO vo) {
		logger.info("/AjaxMenu/GetMenuAuth.do");
		
		MenuVO menuVO = new MenuVO();
		try {
			menuVO = menuService.getMenuAuth(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return menuVO;
	}
	
	@PostMapping("/GetCrawlMenu.do")
	public @ResponseBody MenuVO GetCrawlMenu(@RequestBody MenuVO vo) {
		logger.info("/AjaxMenu/GetCrawlMenu.do");
		
		MenuVO menuVO = new MenuVO();
		try {
			menuVO = menuService.getCrawlMenu(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return menuVO;
	}
	
	@PostMapping("/GetBotMenu.do")
	public @ResponseBody MenuVO GetBotMenu(@RequestBody MenuVO vo) {
		logger.info("/AjaxMenu/GetBotMenu.do");
		
		MenuVO menuVO = new MenuVO();
		try {
			menuVO = menuService.getBotMenu(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return menuVO;
	}
}

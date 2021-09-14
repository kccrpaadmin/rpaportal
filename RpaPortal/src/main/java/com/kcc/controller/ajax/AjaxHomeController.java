package com.kcc.controller.ajax;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.kcc.biz.model.HomeVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.IHomeService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/AjaxHome")
@Controller
public class AjaxHomeController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxHomeController.class);
	
	@Resource(name="homeService")
	private IHomeService homeService;
	
	@PostMapping("/ListTimeLine.do")
	public @ResponseBody List<HomeVO> ListTimeLine(@RequestBody HomeVO vo) {
		logger.info("/AjaxHome/ListTimeLine.do");
		
		SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String curDate = yyyyMMddHHmm.format(new Date()).toString();
		vo.setCallDate(curDate);
		
		List<HomeVO> listHomeVO = new ArrayList<HomeVO>();
		try {
			listHomeVO = homeService.listTimeLine(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listHomeVO;
	}
	
	@PostMapping("/ListDeptRunTime.do")
	public @ResponseBody List<HomeVO> ListDeptRunTime(@RequestBody HomeVO vo) {
		logger.info("/AjaxHome/ListDeptRunTime.do");
		
		List<HomeVO> listHomeVO = new ArrayList<HomeVO>();
		try {
			listHomeVO = homeService.listDeptRunTime(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listHomeVO;
	}
	
}

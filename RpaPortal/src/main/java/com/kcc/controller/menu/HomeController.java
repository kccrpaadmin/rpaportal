package com.kcc.controller.menu;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.BoardVO;
import com.kcc.biz.model.HomeVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;
import com.kcc.biz.service.IHomeService;
import com.kcc.biz.service.IBoardService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/Home")
@Controller
public class HomeController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name="homeService")
	private IHomeService homeService;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@GetMapping("/Home.do")
	public String Home(HttpServletRequest req, HttpServletResponse res, HttpSession session, Principal principal, Locale locale, Model model) {
		logger.info("/Home/Home.do");
		
		HomeVO getHomeTaskRunTimeVO = new HomeVO();
		HomeVO getHomeNoticeTaskVO = new HomeVO();
		BoardVO vo = new BoardVO();
		List<BoardVO> listBoardVO = new ArrayList<BoardVO>();
		vo.setSearchTxt("");
		
		try {
			getHomeTaskRunTimeVO = homeService.getTaskRunTime();
			getHomeNoticeTaskVO = homeService.getNoticeTask();
			listBoardVO = boardService.listBasicBoard(vo);
			//model.addAttribute("eaisSiteLocationComboText", botEaisService.getEaisSiteLocationCombo("SiteLocation"));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		model.addAttribute("getHomeTaskRunTimeVO", getHomeTaskRunTimeVO);
		model.addAttribute("getHomeNoticeTaskVO", getHomeNoticeTaskVO);
		model.addAttribute("listBoardVO", listBoardVO);
		
		return "Home/Home";
	}
}

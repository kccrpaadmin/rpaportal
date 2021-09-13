package com.kcc.controller.ajax;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.BoardVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.IBoardService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/AjaxBoard")
@Controller
public class AjaxBoardController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxBoardController.class);
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	// 공지사항 목록 조회
	@PostMapping("/ListBasicBoard.do")
	public @ResponseBody Map<String, Object> ListBasicBoardl(@RequestBody BoardVO vo) {
		logger.info("/AjaxBoard/ListBasicBoard.do");
		
		List<BoardVO> listBoardVO = new ArrayList<BoardVO>();
		try {
			listBoardVO = boardService.listBasicBoard(vo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Map map = new HashMap<String, Object>();
		map.put("data", listBoardVO);
		
		return map;
	}
	
    //	공지사항 삭제
	@PostMapping("/DeleteBoard.do")
	public @ResponseBody StatusVO DeleteBoard(@RequestBody BoardVO vo) {
			logger.info("/AjaxBoard/DeleteBoard.do");
			String status = "Success";
			
			try {
				boardService.deleteBoard(vo);
			} 
			catch (Exception e) {
				status = "SaveError";
				e.printStackTrace();
			}
			
			StatusVO statusVO = new StatusVO();
			statusVO.setStatus(status);
			
			return statusVO;
	 }
	
}

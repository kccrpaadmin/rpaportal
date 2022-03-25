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
import com.kcc.biz.model.BotEngineerVO;
import com.kcc.biz.model.CodeVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ICodeService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/AjaxAdmin")
@Controller
public class AjaxAdminController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxAdminController.class);
	
	@Resource(name="codeService")
	private ICodeService codeService;
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	// 공통코드 트리 목록 조회
	@PostMapping("/ListCodeTree.do")
	public @ResponseBody List<CodeVO> ListCodeTree(@RequestBody CodeVO vo) {
		logger.info("/AjaxAdmin/ListCodeTree.do");
		
		List<CodeVO> listCodeVO = new ArrayList<CodeVO>();
		try {
			listCodeVO = codeService.listCodeTree(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listCodeVO;
	}		
	
	// 공통코드 자식 목록 조회
	@PostMapping("/ListCodeChild.do")
	public @ResponseBody Map<String, Object> ListCodeChild(@RequestBody CodeVO vo) {
		logger.info("/AjaxAdmin/ListCodeChild.do");
		
		List<CodeVO> listCodeVO = new ArrayList<CodeVO>();
		try {
			listCodeVO = codeService.listCodeChild(vo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Map map = new HashMap<String, Object>();
		map.put("data", listCodeVO);
		
		return map;
	}
	
	// 공통코드 저장
	@PostMapping("/SaveCodeManage.do")
	public @ResponseBody StatusVO SaveCodeManage(@RequestBody CodeVO[] vo) {
		logger.info("/AjaxAdmin/SaveCodeManage.do");
		String status = "Success";
		
		List<CodeVO> inListCodeVO = new ArrayList<CodeVO>();
		
		// json-simple 사용시 배열 파라미터 사용 가능
		for (CodeVO codeVO : vo) {
			inListCodeVO.add(codeVO);
		}
		
		try {
			codeService.saveCodeManage(inListCodeVO);
		} 
		catch (Exception e) {
			status = "SaveError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	// 메뉴 트리 목록 조회
	@PostMapping("/ListMenuTree.do")
	public @ResponseBody List<MenuVO> ListMenuTree(@RequestBody MenuVO vo) {
		logger.info("/AjaxAdmin/ListMenuTree.do");
		
		List<MenuVO> listMenuVO = new ArrayList<MenuVO>();
		try {
			listMenuVO = menuService.listMenuTree(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listMenuVO;
	}	
		
	// 메뉴 자식 목록 조회
	@PostMapping("/ListMenuChild.do")
	public @ResponseBody Map<String, Object> ListMenuChild(@RequestBody MenuVO vo) {
		logger.info("/AjaxAdmin/ListMenuChild.do");
		
		List<MenuVO> listMenuVO = new ArrayList<MenuVO>();
		try {
			listMenuVO = menuService.listMenuChild(vo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Map map = new HashMap<String, Object>();
		map.put("data", listMenuVO);
		
		return map;
	}	
	
	// 메뉴 저장
	@PostMapping("/SaveMenuManage.do")
	public @ResponseBody StatusVO SaveMenuManage(@RequestBody MenuVO[] vo) {
		logger.info("/AjaxAdmin/SaveMenuManage.do");
		String status = "Success";
		
		List<MenuVO> inListMenuVO = new ArrayList<MenuVO>();
		
		// json-simple 사용시 배열 파라미터 사용 가능
		for (MenuVO menuVO : vo) {
			inListMenuVO.add(menuVO);
		}
		
		try {
			menuService.saveMenuManage(inListMenuVO);
		} 
		catch (Exception e) {
			status = "SaveError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}	
	
	// 업무수행 권한 자식 목록 조회
	@PostMapping("/ListMenuAuthRunChild.do")
	public @ResponseBody Map<String, Object> ListMenuAuthRunChild(@RequestBody MenuVO vo) {
		logger.info("/AjaxAdmin/ListMenuAuthRunChild.do");
		
		List<MenuVO> listMenuVO = new ArrayList<MenuVO>();
		try {
			listMenuVO = menuService.listMenuAuthRunChild(vo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Map map = new HashMap<String, Object>();
		map.put("data", listMenuVO);
		
		return map;
	}
	
	// 업무관리 권한 자식 목록 조회
	@PostMapping("/ListMenuAuthManageChild.do")
	public @ResponseBody Map<String, Object> ListMenuAuthManageChild(@RequestBody MenuVO vo) {
		logger.info("/AjaxAdmin/ListMenuAuthManageChild.do");
		
		List<MenuVO> listMenuVO = new ArrayList<MenuVO>();
		try {
			listMenuVO = menuService.listMenuAuthManageChild(vo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Map map = new HashMap<String, Object>();
		map.put("data", listMenuVO);
		
		return map;
	}
	
	// 권한관리 직원 검색
	@PostMapping("/ListAuthManageSearchUser.do")
	public @ResponseBody Map<String, Object> ListAuthManageSearchUser(@RequestBody MenuVO vo) {
		logger.info("/AjaxAdmin/ListAuthManageSearchUser.do");
		
		List<MenuVO> outListAuthManageTargetUserVO = new ArrayList<MenuVO>();
		try {
			outListAuthManageTargetUserVO = menuService.listAuthManageSearchUser(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListAuthManageTargetUserVO);
		
		return map;
	}
	
	// 권한관리 직급 검색
	@PostMapping("/ListAuthManageSearchDegree.do")
	public @ResponseBody Map<String, Object> ListAuthManageSearchDegree(@RequestBody MenuVO vo) {
		logger.info("/AjaxAdmin/ListAuthManageSearchDegree.do");
		
		List<MenuVO> outListAuthManageTargetDegreeVO = new ArrayList<MenuVO>();
		try {
			outListAuthManageTargetDegreeVO = menuService.listAuthManageSearchDegree(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListAuthManageTargetDegreeVO);
		
		return map;
	}
	
	// 권한관리 부서 검색
	@PostMapping("/ListAuthManageSearchDept.do")
	public @ResponseBody Map<String, Object> ListAuthManageSearchDept(@RequestBody MenuVO vo) {
		logger.info("/AjaxAdmin/ListAuthManageSearchDept.do");
		
		List<MenuVO> outListAuthManageTargetDeptVO = new ArrayList<MenuVO>();
		try {
			outListAuthManageTargetDeptVO = menuService.listAuthManageSearchDept(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListAuthManageTargetDeptVO);
		
		return map;
	}
	
	// 권한관리 권한 저장
	@PostMapping("/CreateAuthManage.do")
	public @ResponseBody StatusVO CreateAuthManage(@RequestBody MenuVO vo) {
		logger.info("/AjaxAdmin/CreateAuthManage.do");
		String status = "Success";
				
		try {
			menuService.createAuthManage(vo);
		} 
		catch (Exception e) {
			status = "SaveError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;		
	}
	
	// 권한관리 저장
	@PostMapping("/SaveAuthManage.do")
	public @ResponseBody StatusVO SaveAuthManage(@RequestBody MenuVO[] vo) {
		logger.info("/AjaxAdmin/SaveAuthManage.do");
		String status = "Success";

		List<MenuVO> inListMenuVO = new ArrayList<MenuVO>();
		
		// json-simple 사용시 배열 파라미터 사용 가능
		for (MenuVO menuVO : vo) {
			inListMenuVO.add(menuVO);
		}
		
		try {
			menuService.deleteAuthManage(inListMenuVO);
		} 
		catch (Exception e) {
			status = "DeleteError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;		
	}
	
	// 메뉴관리 부서 검색
	@PostMapping("/ListMenuManageSearchDept.do")
	public @ResponseBody Map<String, Object> ListMenuManageSearchDept(@RequestBody MenuVO vo) {
			logger.info("/AjaxAdmin/ListMenuManageSearchDept.do");
			
			List<MenuVO> outListMenuManageTargetDeptVO = new ArrayList<MenuVO>();
			try {
				outListMenuManageTargetDeptVO = menuService.listMenuManageSearchDept(vo);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			Map map = new HashMap<String, Object>();
			map.put("data", outListMenuManageTargetDeptVO);
			
			return map;
		}
}

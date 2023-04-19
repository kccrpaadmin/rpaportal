package com.kcc.controller.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kcc.biz.model.AnalysisVO;
import com.kcc.biz.model.CodeVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.biz.service.IAnalysisService;
import com.kcc.biz.service.ICodeService;
import com.kcc.biz.service.IMenuService;
import com.kcc.controller.base.BaseController;

@RequestMapping("/AjaxAdmin")
@Controller
public class AjaxAdminController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxAdminController.class);
	
	@Resource(name="codeService")
	private ICodeService codeService;
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	@Resource(name="analysisService")
	private IAnalysisService analysisService;
	
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
	
	@PostMapping("/ListProcessVisits.do")
	public @ResponseBody List<AnalysisVO> ListProcessVisists(@RequestBody AnalysisVO vo) {
		logger.info("/AjaxAdmin/ListProcessVisits.do");
		
		List<AnalysisVO> listAnalysisVO = new ArrayList<AnalysisVO>();
		try {
			listAnalysisVO = analysisService.ListProcessVisits(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listAnalysisVO;
	}
	
	@PostMapping("/ListVisitUser.do")
	public @ResponseBody Map<String, Object> ListVisitUser(@RequestBody AnalysisVO vo) {
		logger.info("/AjaxAdmin/ListVisitUser.do");
		
		List<AnalysisVO> listAnalysisVO = new ArrayList<AnalysisVO>();
		try {
			listAnalysisVO = analysisService.ListVisitUser(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", listAnalysisVO);
		
		return map;
	}
	
	@PostMapping("/ListProcessResult.do")
	public @ResponseBody List<AnalysisVO> ListProcessResult(@RequestBody AnalysisVO vo) {
		logger.info("/AjaxAdmin/ListProcessResult.do");
		
		List<AnalysisVO> listAnalysisVO = new ArrayList<AnalysisVO>();
		try {
			listAnalysisVO = analysisService.ListProcessResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listAnalysisVO;
	}
	
	@PostMapping("/ListProcessResultTot.do")
	public @ResponseBody AnalysisVO ListProcessResultTot(@RequestBody AnalysisVO vo) {
		logger.info("/AjaxAdmin/ListProcessResultTot.do");
		
		AnalysisVO analysisVO = new AnalysisVO();
		try {
			analysisVO = analysisService.getProcessResultTot(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return analysisVO;
	}
}

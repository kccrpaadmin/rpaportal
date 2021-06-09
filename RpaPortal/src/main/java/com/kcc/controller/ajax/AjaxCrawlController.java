package com.kcc.controller.ajax;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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
import com.kcc.biz.model.CrawlG2bVO;
import com.kcc.biz.model.CrawlKomisVO;
import com.kcc.biz.model.CrawlPersonInfoVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.CrawlRunVO;
import com.kcc.biz.model.CrawlScheduleVO;
import com.kcc.biz.model.CrawlSystemCheckVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.ICrawlRequestService;
import com.kcc.biz.service.ICrawlScheduleService;
import com.kcc.biz.service.ICrawlSystemCheckService;
import com.kcc.biz.service.ICrawlG2bService;
import com.kcc.biz.service.ICrawlKomisService;
import com.kcc.biz.service.ICrawlPersonInfoService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.ICrawlUtilService;
import com.kcc.util.service.IRouteUtilService;
import com.kcc.words.ConstWord;

@RequestMapping("/AjaxCrawl")
@Controller
public class AjaxCrawlController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxCrawlController.class);

	@Resource(name="crawlUtilService")
	private ICrawlUtilService crawlUtilService;
	
	@Resource(name="crawlScheduleService")
	private ICrawlScheduleService crawlScheduleService;
	
	@Resource(name="crawlRequestService")
	private ICrawlRequestService crawlRequestService;
	
	@Resource(name="crawlSystemCheckService")
	private ICrawlSystemCheckService crawlSystemCheckService;
	
	@Resource(name="crawlG2bService")
	private ICrawlG2bService crawlG2bService;
	
	@Resource(name="crawlKomisService")
	private ICrawlKomisService crawlKomisService;
	
	@Resource(name="crawlPersonInfoService")
	private ICrawlPersonInfoService crawlPersonInfoService;
	
	@PostMapping("/RunCrawl.do")
	public @ResponseBody StatusVO RunCrawl(@RequestBody CrawlRequestVO vo) {
		logger.info("/AjaxCrawl/RunCrawl.do");
		String status = "Progress";
				
		// RequestVO 출력
		CrawlRequestVO outCrawlRequestVO = new CrawlRequestVO();
		
		try {
			// 웹크롤링 메뉴별 진행여부 조회
			outCrawlRequestVO = crawlRequestService.getCrawlRequestStatus(vo);
		} 
		catch (Exception e) {
			status = "ListSearchError";
			e.printStackTrace();
		}
		
		// 진행여부를 판단
		if ("Stop".equals(outCrawlRequestVO.getRequestStatus())) {
			try {
				// 웹크롤링 요청 정보 생성
				crawlRequestService.createCrawlRequest(vo);
			} 
			catch (Exception e) {
				status = "CreateError";
				e.printStackTrace();
			}
			
			try {
				// CrawlRunVO 입력
				CrawlRunVO inCrawlRunVO = new CrawlRunVO();
				inCrawlRunVO.setMenuId(vo.getMenuId());
				inCrawlRunVO.setEmpNo(vo.getEmpNo());
				inCrawlRunVO.setRequestNo(vo.getNewRequestNo());
				
				// 웹크롤링 서버에 과제 요청
				status = crawlUtilService.requestCrawl(inCrawlRunVO);
			} 
			catch (Exception e) {
				status = "RequestError";
				e.printStackTrace();
			}
		}
		
		// 요청이 실패한 경우
		if ("Fail".equals(status) || "RequestError".equals(status)) {
			// RequestVO 입력
			vo.setRequestNo(vo.getNewRequestNo());
			vo.setErrorMsg("웹크롤링 서버 요청시 오류가 발생 하였습니다.");
			vo.setStatusCd("RA004003");
			
			try {
				// 웹크롤링 요청 정보 변경
				crawlRequestService.updateCrawlRequest(vo);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	@PostMapping("/CreateSchedule.do")
	public @ResponseBody StatusVO CreateSchedule(@RequestBody CrawlScheduleVO vo) {
		logger.info("/AjaxCrawl/CreateSchedule.do");
		String status = "Success";
		
		try {
			crawlScheduleService.createCrawlSchedule(vo);
		} 
		catch (Exception e) {
			status = "CreateError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	@PostMapping("/DeleteSchedule.do")
	public @ResponseBody StatusVO DeleteSchedule(@RequestBody CrawlScheduleVO vo) {
		logger.info("/AjaxCrawl/DeleteSchedule.do");
		String status = "Success";
		
		try {
			crawlScheduleService.deleteCrawlSchedule(vo);
		} 
		catch (Exception e) {
			status = "DeleteError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	@PostMapping("/ListSchedule.do")
	public @ResponseBody Map<String, Object> ListSchedule(@RequestBody CrawlScheduleVO vo) {
		logger.info("/AjaxCrawl/ListSchedule.do");
		
		List<CrawlScheduleVO> outListCrawlScheduleVO = new ArrayList<CrawlScheduleVO>();
		try {
			outListCrawlScheduleVO = crawlScheduleService.listCrawlSchedule(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListCrawlScheduleVO);
		
		return map;
	}
	
	@PostMapping("/ListRequest.do")
	public @ResponseBody Map<String, Object> ListRequest(@RequestBody CrawlRequestVO vo) {
		logger.info("/AjaxCrawl/ListRequest.do");
		
		List<CrawlRequestVO> outListCrawlRequestVO = new ArrayList<CrawlRequestVO>();
		try {
			outListCrawlRequestVO = crawlRequestService.listCrawlRequest(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListCrawlRequestVO);
		
		return map;
	}
	
	@PostMapping("/ListSystemCheckList.do")
	public @ResponseBody Map<String, Object> ListSystemCheckList(@RequestBody CrawlSystemCheckVO vo) {
		logger.info("/AjaxCrawl/ListSystemCheckList.do");
		
		List<CrawlSystemCheckVO> outListCrawlSystemCheckVO = new ArrayList<CrawlSystemCheckVO>();
		try {
			outListCrawlSystemCheckVO = crawlSystemCheckService.listCrawlSystemCheckList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListCrawlSystemCheckVO);
		
		return map;
	}
	
	@PostMapping("/ListG2b.do")
	public @ResponseBody Map<String, Object> ListG2b(@RequestBody CrawlG2bVO vo) {
		logger.info("/AjaxCrawl/ListG2b.do");
		
		List<CrawlG2bVO> outListCrawlG2bVO = new ArrayList<CrawlG2bVO>();
		try {
			outListCrawlG2bVO = crawlG2bService.listCrawlG2b(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListCrawlG2bVO);
		
		return map;
	}
	
	@PostMapping("/ListG2bManage.do")
	public @ResponseBody Map<String, Object> ListG2bManage(@RequestBody CrawlG2bVO vo) {
		logger.info("/AjaxCrawl/ListG2bManage.do");
		
		List<CrawlG2bVO> outListCrawlG2bVO = new ArrayList<CrawlG2bVO>();
		try {
			outListCrawlG2bVO = crawlG2bService.listCrawlG2bManage(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListCrawlG2bVO);
		
		return map;
	}

	@PostMapping("/SaveG2bManage.do")
	public @ResponseBody StatusVO SaveG2bManage(@RequestBody CrawlG2bVO[] vo) {
		logger.info("/AjaxCrawl/SaveG2bManage.do");
		String status = "Success";
		
		List<CrawlG2bVO> inListCrawlG2bVO = new ArrayList<CrawlG2bVO>();
		
		// json-simple 사용시 배열 파라미터 사용 가능
		for (CrawlG2bVO crawlG2bVO : vo) {
			inListCrawlG2bVO.add(crawlG2bVO);
		}
		
		try {
			crawlG2bService.updateCrawlG2bManage(inListCrawlG2bVO);
		} 
		catch (Exception e) {
			status = "UpdateError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	@PostMapping("/ListKomis.do")
	public @ResponseBody Map<String, Object> ListKomis(@RequestBody CrawlKomisVO vo) {
		logger.info("/AjaxCrawl/ListKomis.do");
		
		List<CrawlKomisVO> outListCrawlKomisVO = new ArrayList<CrawlKomisVO>();
		try {
			outListCrawlKomisVO = crawlKomisService.listCrawlKomis(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListCrawlKomisVO);
		
		return map;
	}
	
	@PostMapping("/ListKomisManage.do")
	public @ResponseBody Map<String, Object> ListKomisManage(@RequestBody CrawlKomisVO vo) {
		logger.info("/AjaxCrawl/ListKomisManage.do");
		
		CrawlKomisVO outMinOrMaxData = new CrawlKomisVO();
		try {
			outMinOrMaxData = crawlKomisService.getCrawlKomisManageMinOrMaxData(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map data = new HashMap<String, Object>();
		List<CrawlKomisVO> outListCrawlKomisVO = new ArrayList<CrawlKomisVO>();
		try {
			outListCrawlKomisVO = crawlKomisService.listCrawlKomisManage(vo);
			data.put("data", outListCrawlKomisVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
		
		List<CrawlKomisVO> outListCrawlKomisVOAverage = new ArrayList<CrawlKomisVO>();
		try {
			outListCrawlKomisVOAverage = crawlKomisService.listCrawlKomisManageAverage(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		Map map = new HashMap<String, Object>();
		map.put("outMinOrMaxData", outMinOrMaxData);
		map.put("outListCrawlKomisVO", data);
		map.put("outListCrawlKomisVOAverage", outListCrawlKomisVOAverage);
				
		return map;
	}
	
	@PostMapping("/ListPersonInfo.do")
	public @ResponseBody Map<String, Object> ListPersonInfo(@RequestBody CrawlPersonInfoVO vo) {
		logger.info("/AjaxCrawl/ListPersonInfo.do");
		
		List<CrawlPersonInfoVO> outListCrawlPersonInfoVO = new ArrayList<CrawlPersonInfoVO>();
		try {
			outListCrawlPersonInfoVO = crawlPersonInfoService.listCrawlPersonInfo(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListCrawlPersonInfoVO);
		
		return map;
	}
	
	@PostMapping("/ListPersonInfoManage.do")
	public @ResponseBody Map<String, Object> ListPersonInfoManage(@RequestBody CrawlPersonInfoVO vo) {
		logger.info("/AjaxCrawl/ListPersonInfoManage.do");
		
		List<CrawlPersonInfoVO> outListCrawlPersonInfoVO = new ArrayList<CrawlPersonInfoVO>();
		try {
			outListCrawlPersonInfoVO = crawlPersonInfoService.listCrawlPersonInfoManage(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListCrawlPersonInfoVO);
		
		return map;
	}	
}

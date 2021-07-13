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

import com.kcc.biz.model.BotEseroVO;
import com.kcc.biz.model.BotRequestVO;
import com.kcc.biz.model.BotScheduleVO;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.CrawlScheduleVO;
import com.kcc.biz.model.FileUploadVO;
import com.kcc.biz.model.OcrRequestVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.biz.model.BotMoneySendVO;
import com.kcc.biz.model.BotCostDivideVO;
import com.kcc.biz.model.BotKisconConstVO;
import com.kcc.biz.model.BotEtcTaxVO;

import com.kcc.biz.service.IBotEseroService;
import com.kcc.biz.service.IBotRequestService;
import com.kcc.biz.service.IBotScheduleService;
import com.kcc.biz.service.ICrawlScheduleService;
import com.kcc.biz.service.IBotMoneySendService;
import com.kcc.biz.service.IBotCostDivideService;
import com.kcc.biz.service.IBotKisconConstService;
import com.kcc.biz.service.IBotEtcTaxService;

import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IBotUtilService;
import com.kcc.util.service.ICrawlUtilService;
import com.kcc.util.service.IFileUploadUtilService;

@RequestMapping("/AjaxBot")
@Controller
public class AjaxBotController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxBotController.class);

	@Resource(name="fileUploadUtilService")
	private IFileUploadUtilService fileUploadUtilService;
	
	@Resource(name="botUtilService")
	private IBotUtilService botUtilService;
	
	@Resource(name="botScheduleService")
	private IBotScheduleService botScheduleService;
	
	@Resource(name="botRequestService")
	private IBotRequestService botRequestService;
	
	@Resource(name="botEseroService")
	private IBotEseroService botEseroService;

	@Resource(name="botMoneySendService")
	private IBotMoneySendService botMoneySendService;
	
	@Resource(name="botCostDivideService")
	private IBotCostDivideService botCostDivideService;
	
	@Resource(name="botKisconConstService")
	private IBotKisconConstService botKisconConstService;
	
	@Resource(name="botEtcTaxService")
	private IBotEtcTaxService botEtcTaxService;
	
	@PostMapping("/RunBot.do")
	public @ResponseBody BotRequestVO RunBot(@RequestBody BotRequestVO vo) {
		logger.info("/AjaxBot/RunBot.do");

		// 봇 수행 (진행중인 경우 수행 안함)
		BotRequestVO outBotRequestVO = new BotRequestVO();
		outBotRequestVO = botUtilService.requestBot(vo);
		
		// 오류 발생시
		if ("Fail".equals(outBotRequestVO.getRequestStatus())) {
			outBotRequestVO.setStatusCd("RA005003");
			outBotRequestVO.setErrorMsg("봇 요청시 오류가 발생 하였습니다.");
			
			try {
				// 봇 요청 정보 변경
				botRequestService.updateBotRequest(outBotRequestVO);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return outBotRequestVO;
	}
	
	@PostMapping("/CreateSchedule.do")
	public @ResponseBody StatusVO CreateSchedule(@RequestBody BotScheduleVO vo) {
		logger.info("/AjaxBot/CreateSchedule.do");
		String status = "Success";
		
		try {
			botScheduleService.createBotSchedule(vo);
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
	public @ResponseBody StatusVO DeleteSchedule(@RequestBody BotScheduleVO vo) {
		logger.info("/AjaxBot/DeleteSchedule.do");
		String status = "Success";
		
		try {
			botScheduleService.deleteBotSchedule(vo);
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
	public @ResponseBody Map<String, Object> ListSchedule(@RequestBody BotScheduleVO vo) {
		logger.info("/AjaxBot/ListSchedule.do");
		
		List<BotScheduleVO> outListBotScheduleVO = new ArrayList<BotScheduleVO>();
		try {
			outListBotScheduleVO = botScheduleService.listBotSchedule(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotScheduleVO);
		
		return map;
	}
	
	@PostMapping("/ListRequest.do")
	public @ResponseBody Map<String, Object> ListRequest(@RequestBody BotRequestVO vo) {
		logger.info("/AjaxBot/ListRequest.do");
		
		List<BotRequestVO> outListBotRequestVO = new ArrayList<BotRequestVO>();
		try {
			outListBotRequestVO = botRequestService.listBotRequest(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotRequestVO);
		
		return map;
	}
	
	// 송금확인증 발급 업무
	@PostMapping("/ListMoneySendList.do")
	public @ResponseBody Map<String, Object> ListMoneySendList(@RequestBody BotMoneySendVO vo) {
		logger.info("/AjaxBot/ListMoneySendList.do");
		
		List<BotMoneySendVO> outListBotMoneySendListVO = new ArrayList<BotMoneySendVO>();
		try {
			outListBotMoneySendListVO = botMoneySendService.listBotMoneySendList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotMoneySendListVO);
		
		return map;
	}
	
		@PostMapping("/ListBotMoneySendManage.do")
		public @ResponseBody Map<String, Object> ListBotMoneySendManage(@RequestBody BotMoneySendVO vo) {
			logger.info("/AjaxBot/ListBotMoneySendManage.do");
			
			List<BotMoneySendVO> outListBotMoneySendManageVO = new ArrayList<BotMoneySendVO>();
			try {
				outListBotMoneySendManageVO = botMoneySendService.listBotMoneySendManage(vo);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			Map map = new HashMap<String, Object>();
			map.put("data", outListBotMoneySendManageVO);
			
			return map;
		}
	
	
	// (세금)계산서, 전표 데이터 대사업무	
	@PostMapping("/SaveEseroTargetDate.do")
	public @ResponseBody StatusVO SaveEseroTargetDate(@RequestBody BotEseroVO vo) {
		logger.info("/AjaxBot/SaveEseroTargetDate.do");
		String status = "Success";
		
		try {
			botEseroService.saveBotEseroTargetDate(vo);
		} 
		catch (Exception e) {
			status = "SaveError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	@PostMapping("/ListEseroTargetDate.do")
	public @ResponseBody Map<String, Object> ListEseroTargetDate(@RequestBody BotEseroVO vo) {
		logger.info("/AjaxBot/ListEseroTargetDate.do");
		
		List<BotEseroVO> outListBotEseroVO = new ArrayList<BotEseroVO>();
		try {
			outListBotEseroVO = botEseroService.listBotEseroTargetDate(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListEseroInvoiceList.do")
	public @ResponseBody Map<String, Object> ListEseroInvoiceList(@RequestBody BotEseroVO vo) {
		logger.info("/AjaxBot/ListEseroInvoiceList.do");
		
		List<BotEseroVO> outListBotEseroVO = new ArrayList<BotEseroVO>();
		try {
			outListBotEseroVO = botEseroService.listBotEseroInvoiceList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListEseroSlipList.do")
	public @ResponseBody Map<String, Object> ListEseroSlipList(@RequestBody BotEseroVO vo) {
		logger.info("/AjaxBot/ListEseroSlipList.do");
		
		List<BotEseroVO> outListBotEseroVO = new ArrayList<BotEseroVO>();
		try {
			outListBotEseroVO = botEseroService.listBotEseroSlipList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	// 매입세 안분 검토 업무
	@PostMapping("/listCostDivideTargetDate.do")
	public @ResponseBody Map<String, Object> listCostDivideTargetDate(@RequestBody BotCostDivideVO vo) {
		logger.info("/AjaxBot/listCostDivideTargetDate.do");
		
		List<BotCostDivideVO> outListBotCostDivideTargetDateVO = new ArrayList<BotCostDivideVO>();
		try {
			outListBotCostDivideTargetDateVO = botCostDivideService.listBotCostDivideTargetDate(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotCostDivideTargetDateVO);
		
		return map;
	}
		
	// 하도급 변경계약 키스콘 등록 업무
	@PostMapping("/listKisconConstSubcontract.do")
	public @ResponseBody Map<String, Object> listKisconConstSubcontract(@RequestBody BotKisconConstVO vo) {
		logger.info("/AjaxBot/listKisconConstSubcontract.do");
		
		List<BotKisconConstVO> outListBotKisconConstSubcontractVO = new ArrayList<BotKisconConstVO>();
		try {
			outListBotKisconConstSubcontractVO = botKisconConstService.listBotKisconConstSubcontract(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotKisconConstSubcontractVO);
		
		return map;
	}
						
	// 기타영수증 전표 세금코드 오라클 입력업무	
	@PostMapping("/UploadFilesEtcTax.do")
	public @ResponseBody StatusVO UploadFilesEtcTax(FileUploadVO fvo, BotRequestVO vo) {
		logger.info("/AjaxBot/UploadFilesEtcTax.do");
		String status = "Fail";
		String errorMsg = "";
		String attId = "";
		
		try {
			// 첨부파일 저장
			attId = fileUploadUtilService.createFiles(fvo.getFiles(), vo.getMenuId(), vo.getEmpNo());
			status = "Success";
		}
		catch (Exception e1) {
			status = "FileSaveError";
			errorMsg = e1.getMessage();
			e1.printStackTrace();
		}		
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		statusVO.setErrorMsg(errorMsg);
		statusVO.setAttId(attId);
		
    	return statusVO;
	}
	
	@PostMapping("/listEtcTaxList.do")
	public @ResponseBody Map<String, Object> listEtcTaxList(@RequestBody BotEtcTaxVO vo) {
		logger.info("/AjaxBot/listEtcTaxList.do");
		
		List<BotEtcTaxVO> outListBotEtcTaxListVO = new ArrayList<BotEtcTaxVO>();
		try {
			outListBotEtcTaxListVO = botEtcTaxService.listBotEtcTaxList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEtcTaxListVO);
		
		return map;
	}
	
    // 업체 조회
	@PostMapping("/listMoneySendManageSearchVendor.do")
	public @ResponseBody Map<String, Object> listMoneySendManageSearchVendor(@RequestBody BotMoneySendVO vo) {
		logger.info("/AjaxBot/listMoneySendManageSearchVendor.do");
		
		List<BotMoneySendVO> outListBotMoneySendManageSearchVendorVO = new ArrayList<BotMoneySendVO>();
		try {
			outListBotMoneySendManageSearchVendorVO = botMoneySendService.listBotMoneySendManageSearchVendor(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotMoneySendManageSearchVendorVO);
		
		return map;
	}
}

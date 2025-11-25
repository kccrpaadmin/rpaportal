package com.kcc.controller.ajax;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
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
import com.kcc.biz.model.BotSalesEseroVO;
import com.kcc.biz.model.BotScheduleVO;
import com.kcc.biz.model.CrawlG2bVO;
import com.kcc.biz.model.FileUploadVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.biz.model.BotMoneySendVO;
import com.kcc.biz.model.BotCostDivideVO;
import com.kcc.biz.model.BotDelayedPaymentVO;
import com.kcc.biz.model.BotEngineerEduVO;
import com.kcc.biz.model.BotKisconConstVO;
import com.kcc.biz.model.BotEtcTaxVO;
import com.kcc.biz.model.BotPersonAppointVO;
import com.kcc.biz.model.BotEngineerVO;
import com.kcc.biz.model.BotBidChangeVO;
import com.kcc.biz.model.BotPersonalBccVO;
import com.kcc.biz.model.BotSCSystemCheckVO;
import com.kcc.biz.model.BotAdDailyReportVO;
import com.kcc.biz.model.BotAutoCADCancelVO;
import com.kcc.biz.model.BotEaisVO;
import com.kcc.biz.model.BotInsuranceVO;
import com.kcc.biz.model.BotXmlVO;
import com.kcc.biz.model.BotSpecialConditionVO;
import com.kcc.biz.model.BotCorporateNoticeVO;
import com.kcc.biz.model.BotCompanyRestrictionVO;
import com.kcc.biz.model.BotContractElecStampTaxVO;
import com.kcc.biz.model.BotSensoryTemperatureVO;
import com.kcc.biz.model.BotCorpCardSendVO;
import com.kcc.biz.model.BotEngineerConstructionVO;
import com.kcc.biz.model.BotStampTaxSlipDataVO;

import com.kcc.biz.service.IBotEseroService;
import com.kcc.biz.service.IBotRequestService;
import com.kcc.biz.service.IBotSalesEseroService;
import com.kcc.biz.service.IBotScheduleService;
import com.kcc.biz.service.IBotMoneySendService;
import com.kcc.biz.service.IBotCostDivideService;
import com.kcc.biz.service.IBotEngineerEduService;
import com.kcc.biz.service.IBotKisconConstService;
import com.kcc.biz.service.IBotEtcTaxService;
import com.kcc.biz.service.IBotPersonAppointService;
import com.kcc.biz.service.IBotEngineerService;
import com.kcc.biz.service.IBotBidChangeService;
import com.kcc.biz.service.IBotPersonalBccService;
import com.kcc.biz.service.IBotSCSystemCheckService;
import com.kcc.biz.service.IBotAdDailyReportService;
import com.kcc.biz.service.IBotAutoCADCancelService;
import com.kcc.biz.service.IBotEaisService;
import com.kcc.biz.service.IBotInsuranceService;
import com.kcc.biz.service.IBotDelayedPaymentService;
import com.kcc.biz.service.IBotXmlService;
import com.kcc.config.PowerAutomateClient;
import com.kcc.biz.service.IBotSpecialConditionService;
import com.kcc.biz.service.IBotCorporateNoticeService;
import com.kcc.biz.service.IBotCompanyRestrictionService;
import com.kcc.biz.service.IBotContractElecStampTaxService;
import com.kcc.biz.service.IBotSensoryTemperatureService;
import com.kcc.biz.service.IBotCorpCardSendService;
import com.kcc.biz.service.IBotEngineerConstructionService;
import com.kcc.biz.service.IBotContractElecStampTaxService;
import com.kcc.biz.service.IBotStampTaxSlipDataService;


import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IBotUtilService;
import com.kcc.util.service.IFileUploadUtilService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/AjaxBot")
@Controller
@ResponseBody
@RequiredArgsConstructor
public class AjaxBotController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxBotController.class);

	private final com.kcc.config.PowerAutomateClient client;
	
	
	
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
	
	@Resource(name="botPersonAppointService")
	private IBotPersonAppointService botPersonAppointService;
	
	@Resource(name="botEngineerService")
	private IBotEngineerService botEngineerService;
	
	@Resource(name="botEngineerEduService")
	private IBotEngineerEduService botEngineerEduService;
	
	@Resource(name="botBidChangeService")
	private IBotBidChangeService botBidChangeService;
	
	@Resource(name="botPersonalBccService")
	private IBotPersonalBccService botPersonalBccService;
	
	@Resource(name="botSalesEseroService")
	private IBotSalesEseroService botSalesEseroService;
	
	@Resource(name="botSCSystemCheckService")
	private IBotSCSystemCheckService botSCSystemCheckService;
	
	@Resource(name="botAdDailyReportService")
	private IBotAdDailyReportService botAdDailyReportService;
	
	@Resource(name="botEaisService")
	private IBotEaisService botEaisService;

	@Resource(name="botInsuranceService")
	private IBotInsuranceService botInsuranceService;
	
	@Resource(name="botDelayedPaymentService")
	private IBotDelayedPaymentService botDelayedPaymentService;
	
	@Resource(name="botXmlService")
	private IBotXmlService botXmlService;
	
	@Resource(name="botSpecialConditionService")
	private IBotSpecialConditionService botSpecialConditionService;
	
	@Resource(name="botCorporateNoticeService")
	private IBotCorporateNoticeService botCorporateNoticeService;
	
	@Resource(name="botCompanyRestrictionService")
	private IBotCompanyRestrictionService botCompanyRestrictionService;
	
	@Resource(name="botContractElecStampTaxService")
	private IBotContractElecStampTaxService botContractElecStampTaxService;
	
	@Resource(name="botSensoryTemperatureService")
	private IBotSensoryTemperatureService botSensoryTemperatureService;
	
	@Resource(name="botAutoCADCancelService")
	private IBotAutoCADCancelService botAutoCADCancelService;
	
	@Resource(name="botCorpCardSendService")
	private IBotCorpCardSendService botCorpCardSendService;
	
	@Resource(name="botEngineerConstructionService")
	private IBotEngineerConstructionService botEngineerConstructionService;
	
	@Resource(name="botStampTaxSlipDataService")
	private IBotStampTaxSlipDataService botStampTaxSlipDataService;
	
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
	
	@PostMapping("/RunPaBot.do")
	public @ResponseBody BotRequestVO RunPaBot(@RequestBody BotRequestVO vo) {
		logger.info("/AjaxBot/RunPaBot.do");

		Map<String,Object> payload = Collections.singletonMap("MenuID", vo.getMenuId());
		
		// 봇 수행 (진행중인 경우 수행 안함)
		BotRequestVO outBotRequestVO = new BotRequestVO();
		outBotRequestVO = botUtilService.requestBotPa(vo);
		
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
	
	@PostMapping("/ListMoneySendManage.do")
	public @ResponseBody Map<String, Object> ListMoneySendManage(@RequestBody BotMoneySendVO vo) {
			logger.info("/AjaxBot/ListMoneySendManage.do");
			
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
	
	@PostMapping("/ListMoneySendManageSearchVendor.do")
	public @ResponseBody Map<String, Object> ListMoneySendManageSearchVendor(@RequestBody BotMoneySendVO vo) {
			logger.info("/AjaxBot/ListMoneySendManageSearchVendor.do");
			
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
		
	@PostMapping("/ListMoneySendManageSendAmt.do")
	public @ResponseBody Map<String, Object> ListMoneySendManageSendAmt(@RequestBody BotMoneySendVO vo) {
			logger.info("/AjaxBot/ListMoneySendManageSendAmt.do");
			
			List<BotMoneySendVO> outListBotMoneySendManageSendAmtVO = new ArrayList<BotMoneySendVO>();
			try {
				outListBotMoneySendManageSendAmtVO = botMoneySendService.listBotMoneySendManageSendAmt(vo);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			Map map = new HashMap<String, Object>();
			map.put("data", outListBotMoneySendManageSendAmtVO);
			
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
	
	@PostMapping("/ListEseroInvoiceSlipListManageTaxOn.do")
	public @ResponseBody Map<String, Object> ListEseroInvoiceSlipListManageTaxOn(@RequestBody BotEseroVO vo) {
		logger.info("/AjaxBot/ListEseroInvoiceSlipListManageTaxOn.do");
		
		List<BotEseroVO> outListBotEseroVO = new ArrayList<BotEseroVO>();
		try {
			outListBotEseroVO = botEseroService.listBotEseroInvoiceSlipListManageTaxOn(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListEseroInvoiceSlipListManageTaxOff.do")
	public @ResponseBody Map<String, Object> ListEseroInvoiceSlipListManageTaxOff(@RequestBody BotEseroVO vo) {
		logger.info("/AjaxBot/ListEseroInvoiceSlipListManageTaxOff.do");
		
		List<BotEseroVO> outListBotEseroVO = new ArrayList<BotEseroVO>();
		try {
			outListBotEseroVO = botEseroService.listBotEseroInvoiceSlipListManageTaxOff(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListEseroInvoiceSlipListManageOraCheckList.do")
	public @ResponseBody Map<String, Object> ListEseroInvoiceSlipListManageOraCheckList(@RequestBody BotEseroVO vo) {
		logger.info("/AjaxBot/ListEseroInvoiceSlipListManageOraCheckList.do");
		
		List<BotEseroVO> outListBotEseroVO = new ArrayList<BotEseroVO>();
		try {
			outListBotEseroVO = botEseroService.listBotEseroInvoiceSlipListManageOraCheckList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListEseroManageVendorInvoiceList.do")
	public @ResponseBody Map<String, Object> ListEseroManageVendorInvoiceList(@RequestBody BotEseroVO vo) {
		logger.info("/AjaxBot/ListEseroManageVendorInvoiceList.do");
		
		List<BotEseroVO> outListBotEseroVO = new ArrayList<BotEseroVO>();
		try {
			outListBotEseroVO = botEseroService.listBotEseroManageVendorInvoiceList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListEseroManageVendorSlipList.do")
	public @ResponseBody Map<String, Object> ListEseroManageVendorSlipList(@RequestBody BotEseroVO vo) {
		logger.info("/AjaxBot/ListEseroManageVendorSlipList.do");
		
		List<BotEseroVO> outListBotEseroVO = new ArrayList<BotEseroVO>();
		try {
			outListBotEseroVO = botEseroService.listBotEseroManageVendorSlipList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	// 매입세 안분 검토 업무
	@PostMapping("/ListCostDivideTargetDate.do")
	public @ResponseBody Map<String, Object> ListCostDivideTargetDate(@RequestBody BotCostDivideVO vo) {
		logger.info("/AjaxBot/ListCostDivideTargetDate.do");
		
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
	@PostMapping("/ListKisconConstSubcontract.do")
	public @ResponseBody Map<String, Object> ListKisconConstSubcontract(@RequestBody BotKisconConstVO vo) {
		logger.info("/AjaxBot/ListKisconConstSubcontract.do");
		
		List<BotKisconConstVO> outListKisconConstSubcontractVO = new ArrayList<BotKisconConstVO>();
		try {
			outListKisconConstSubcontractVO = botKisconConstService.listKisconConstSubcontract(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListKisconConstSubcontractVO);
		
		return map;
	}
	
	@PostMapping("/ListKisconConstManage.do")
	public @ResponseBody Map<String, Object> ListKisconConstManage(@RequestBody BotKisconConstVO vo) {
		logger.info("/AjaxBot/ListKisconConstManage.do");
		
		List<BotKisconConstVO> outListKisconConstManageVO = new ArrayList<BotKisconConstVO>();
		try {
			outListKisconConstManageVO = botKisconConstService.listKisconConstManage(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListKisconConstManageVO);
		
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
	
	@PostMapping("/ListEtcTaxList.do")
	public @ResponseBody Map<String, Object> ListEtcTaxList(@RequestBody BotEtcTaxVO vo) {
		logger.info("/AjaxBot/ListEtcTaxList.do");
		
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
	
	// 그룹사 인사발령 수집 업무
	@PostMapping("/ListPersonAppointList.do")
	public @ResponseBody Map<String, Object> ListPersonAppointList(@RequestBody BotPersonAppointVO vo) {
		logger.info("/AjaxBot/ListPersonAppointList.do");
		
		List<BotPersonAppointVO> outListPersonAppointListVO = new ArrayList<BotPersonAppointVO>();
		try {
			outListPersonAppointListVO = botPersonAppointService.listBotPersonAppointList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListPersonAppointListVO);
		
		return map;
	}
	
	// 기술인협회 기술자정보 수집 업무
	@PostMapping("/ListEngineerBasic.do")
	public @ResponseBody Map<String, Object> ListEngineerBasic(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/ListEngineerBasic.do");
		
		List<BotEngineerVO> outListEngineerBasicVO = new ArrayList<BotEngineerVO>();
		try {
			outListEngineerBasicVO = botEngineerService.listBotEngineerBasic(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEngineerBasicVO);
		
		return map;
	}
	
	@PostMapping("/ListEngineerQualityRank.do")
	public @ResponseBody Map<String, Object> ListEngineerQualityRank(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/ListEngineerQualityRank.do");
		
		List<BotEngineerVO> outListEngineerQualityRankVO = new ArrayList<BotEngineerVO>();
		try {
			outListEngineerQualityRankVO = botEngineerService.listBotEngineerQualityRank(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEngineerQualityRankVO);
		
		return map;
	}
	
	@PostMapping("/ListEngineerQualityEduPass.do")
	public @ResponseBody Map<String, Object> ListEngineerQualityEduPass(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/ListEngineerQualityEduPass.do");
		
		List<BotEngineerVO> outListEngineerQualityEduPassVO = new ArrayList<BotEngineerVO>();
		try {
			outListEngineerQualityEduPassVO = botEngineerService.listBotEngineerQualityEduPass(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEngineerQualityEduPassVO);
		
		return map;
	}
	
	@PostMapping("/ListEngineerQualityEduFail.do")
	public @ResponseBody Map<String, Object> ListEngineerQualityEduFail(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/ListEngineerQualityEduFail.do");
		
		List<BotEngineerVO> outListEngineerQualityEduFailVO = new ArrayList<BotEngineerVO>();
		try {
			outListEngineerQualityEduFailVO = botEngineerService.listBotEngineerQualityEduFail(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEngineerQualityEduFailVO);
		
		return map;
	}
	
	@PostMapping("/ListEngineerCareerList.do")
	public @ResponseBody Map<String, Object> ListEngineerCareerList(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/ListEngineerCareerList.do");
		
		List<BotEngineerVO> outListEngineerCareerListVO = new ArrayList<BotEngineerVO>();
		try {
			outListEngineerCareerListVO = botEngineerService.listBotEngineerCareerList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEngineerCareerListVO);
		
		return map;
	}
	
	@PostMapping("/ListEngineerManage.do")
	public @ResponseBody Map<String, Object> ListEngineerManage(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/ListEngineerManage.do");
		
		List<BotEngineerVO> outListEngineerManageVO = new ArrayList<BotEngineerVO>();
		try {
			outListEngineerManageVO = botEngineerService.listBotEngineerManage(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEngineerManageVO);
		
		return map;
	}
	
	@PostMapping("/ListEngineerManageCareerList.do")
	public @ResponseBody Map<String, Object> ListEngineerManageCareerList(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/ListEngineerManageCareerList.do");
		
		List<BotEngineerVO> outListEngineerManageCareerListVO = new ArrayList<BotEngineerVO>();
		try {
			outListEngineerManageCareerListVO = botEngineerService.listBotEngineerManageCareerList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEngineerManageCareerListVO);
		
		return map;
	}
	
	@PostMapping("/ListEngineerTargetUserList.do")
	public @ResponseBody Map<String, Object> ListEngineerTargetUserList(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/ListEngineerTargetUserList.do");
		
		List<BotEngineerVO> outListEngineerTargetUserVO = new ArrayList<BotEngineerVO>();
		try {
			outListEngineerTargetUserVO = botEngineerService.listBotEngineerTargetUserList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEngineerTargetUserVO);
		
		return map;
	}
	
	@PostMapping("/SaveEngineerTargetUser.do")
	public @ResponseBody StatusVO SaveEngineerTargetUser(@RequestBody BotEngineerVO[] vo) {
		logger.info("/AjaxBot/SaveEngineerTargetUser.do");
		String status = "Success";

		List<BotEngineerVO> inListBotEngineerVO = new ArrayList<BotEngineerVO>();
		
		// json-simple 사용시 배열 파라미터 사용 가능
		for (BotEngineerVO botEngineerVO : vo) {
			inListBotEngineerVO.add(botEngineerVO);
		}
		
		try {
			botEngineerService.deleteBotEngineerTargetUser(inListBotEngineerVO);
		} 
		catch (Exception e) {
			status = "DeleteError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;		
	}
	
	@PostMapping("/CreateEngineerTargetUser.do")
	public @ResponseBody StatusVO CreateEngineerTargetUser(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/CreateEngineerTargetUser.do");
		String status = "Success";
				
		try {
			botEngineerService.createBotEngineerTargetUser(vo);
		} 
		catch (Exception e) {
			status = "SaveError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;		
	}
	
	@PostMapping("/ListEngineerTargetUserSearchUser.do")
	public @ResponseBody Map<String, Object> ListEngineerTargetUserSearchUser(@RequestBody BotEngineerVO vo) {
		logger.info("/AjaxBot/ListEngineerTargetUserSearchUser.do");
		
		List<BotEngineerVO> outListEngineerTargetUserVO = new ArrayList<BotEngineerVO>();
		try {
			outListEngineerTargetUserVO = botEngineerService.listBotEngineerTargetUserSearchUser(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEngineerTargetUserVO);
		
		return map;
	}		
	
	@PostMapping("/ListBotEngineerConstEduPass.do")
	public @ResponseBody Map<String, Object> ListBotEngineerConstEduPass(@RequestBody BotEngineerEduVO vo) {
		logger.info("/AjaxBot/ListBotEngineerConstEduPass.do");
		
		List<BotEngineerEduVO> outListBotEngineerConstEduPassVO = new ArrayList<BotEngineerEduVO>();
		try {
			outListBotEngineerConstEduPassVO = botEngineerEduService.listBotEngineerConstEduPass(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEngineerConstEduPassVO);
		
		return map;
	}
	
	@PostMapping("/ListBotEngineerConstEduFail.do")
	public @ResponseBody Map<String, Object> ListBotEngineerConstEduFail(@RequestBody BotEngineerEduVO vo) {
		logger.info("/AjaxBot/ListBotEngineerConstEduFail.do");
		
		List<BotEngineerEduVO> outListBotEngineerConstEduFailVO = new ArrayList<BotEngineerEduVO>();
		try {
			outListBotEngineerConstEduFailVO = botEngineerEduService.listBotEngineerConstEduFail(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEngineerConstEduFailVO);
		
		return map;
	}
	
	@PostMapping("/ListBotEngineerEduManageQualityEdu.do")
	public @ResponseBody Map<String, Object> ListBotEngineerEduManageQualityEdu(@RequestBody BotEngineerEduVO vo) {
		logger.info("/AjaxBot/ListBotEngineerEduManageQualityEdu.do");
		
		List<BotEngineerEduVO> outListQualityEduVO = new ArrayList<BotEngineerEduVO>();
		try {
			outListQualityEduVO = botEngineerEduService.listBotEngineerEduManageQualityEdu(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListQualityEduVO);
		
		return map;
	}
	
	@PostMapping("/ListBotEngineerEduManageConstEdu.do")
	public @ResponseBody Map<String, Object> ListBotEngineerEduManageConstEdu(@RequestBody BotEngineerEduVO vo) {
		logger.info("/AjaxBot/ListBotEngineerEduManageConstEdu.do");
		
		List<BotEngineerEduVO> outListConstEduVO = new ArrayList<BotEngineerEduVO>();
		try {
			outListConstEduVO = botEngineerEduService.listBotEngineerEduManageConstEdu(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListConstEduVO);
		
		return map;
	}
	
	@PostMapping("/ListBidChangeG2b.do")
	public @ResponseBody Map<String, Object> ListBidChangeG2b(@RequestBody BotBidChangeVO vo) {
		logger.info("/AjaxBot/ListBidChangeG2b.do");
		
		List<BotBidChangeVO> outListBidChangeG2bVO = new ArrayList<BotBidChangeVO>();
		try {
			outListBidChangeG2bVO = botBidChangeService.listBotBidChangeG2b(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBidChangeG2bVO);
		
		return map;
	}
	
	@PostMapping("/ListBidChangeTargetBid.do")
	public @ResponseBody Map<String, Object> ListBidChangeTargetBid(@RequestBody BotBidChangeVO vo) {
		logger.info("/AjaxBot/ListBidChangeTargetBid.do");
		
		List<BotBidChangeVO> outListBidChangeTargetBidVO = new ArrayList<BotBidChangeVO>();
		try {
			outListBidChangeTargetBidVO = botBidChangeService.listBotBidChangeTargetBid(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBidChangeTargetBidVO);
		
		return map;
	}
	
	@PostMapping("/CreateBotBidChangeTargetBid.do")
	public @ResponseBody StatusVO CreateBotBidChangeTargetBid(@RequestBody BotBidChangeVO[] vo) {
		logger.info("/AjaxBot/CreateBotBidChangeTargetBid.do");
		String status = "Success";
				
		List<BotBidChangeVO> inListBotBidChangeVO = new ArrayList<BotBidChangeVO>();
		
		// json-simple 사용시 배열 파라미터 사용 가능
		for (BotBidChangeVO botBidChangeVO : vo) {
			inListBotBidChangeVO.add(botBidChangeVO);
		}
		
		try {
			botBidChangeService.createBotBidChangeTargetBid(inListBotBidChangeVO);
		} 
		catch (Exception e) {
			status = "SaveError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;		
	}
	
	@PostMapping("/DeleteBotBidChangeTargetBid.do")
	public @ResponseBody StatusVO DeleteBotBidChangeTargetBid(@RequestBody BotBidChangeVO[] vo) {
		logger.info("/AjaxBot/DeleteBotBidChangeTargetBid.do");
		String status = "Success";
				
		List<BotBidChangeVO> inListBotBidChangeVO = new ArrayList<BotBidChangeVO>();
		
		// json-simple 사용시 배열 파라미터 사용 가능
		for (BotBidChangeVO botBidChangeVO : vo) {
			inListBotBidChangeVO.add(botBidChangeVO);
		}
		
		try {
			botBidChangeService.deleteBotBidChangeTargetBid(inListBotBidChangeVO);
		} 
		catch (Exception e) {
			status = "SaveError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;		
	}
	
	@PostMapping("/ListBidChange.do")
	public @ResponseBody Map<String, Object> ListBidChange(@RequestBody BotBidChangeVO vo) {
		logger.info("/AjaxBot/ListBidChange.do");
		
		List<BotBidChangeVO> outListBidChangeVO = new ArrayList<BotBidChangeVO>();
		try {
			outListBidChangeVO = botBidChangeService.listBotBidChange(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBidChangeVO);
		
		return map;
	}
	
	@PostMapping("/ListPersonalBcc.do")
	public @ResponseBody Map<String, Object> ListPersonalBcc(@RequestBody BotPersonalBccVO vo) {
		logger.info("/AjaxBot/ListPersonalBcc.do");
		
		List<BotPersonalBccVO> outListPersonalBccVO = new ArrayList<BotPersonalBccVO>();
		try {
			outListPersonalBccVO = botPersonalBccService.listPersonalBcc(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListPersonalBccVO);
		
		return map;
	}
	
	// 매출 (세금)계산서, 전표 데이터 대사업무	
	@PostMapping("/SaveSalesEseroTargetDate.do")
	public @ResponseBody StatusVO SaveSalesEseroTargetDate(@RequestBody BotSalesEseroVO vo) {
		logger.info("/AjaxBot/SaveSalesEseroTargetDate.do");
		String status = "Success";
		
		try {
			botSalesEseroService.saveBotSalesEseroTargetDate(vo);
		} 
		catch (Exception e) {
			status = "SaveError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	@PostMapping("/ListSalesEseroTargetDate.do")
	public @ResponseBody Map<String, Object> ListSalesEseroTargetDate(@RequestBody BotSalesEseroVO vo) {
		logger.info("/AjaxBot/ListEseroTargetDate.do");
		
		List<BotSalesEseroVO> outListBotEseroVO = new ArrayList<BotSalesEseroVO>();
		try {
			outListBotEseroVO = botSalesEseroService.listBotSalesEseroTargetDate(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListICHomeTaxList.do")
	public @ResponseBody Map<String, Object> ListICHomeTaxList(@RequestBody BotSalesEseroVO vo) {
		logger.info("/AjaxBot/ListICHomeTaxList.do");
		
		List<BotSalesEseroVO> outListBotEseroVO = new ArrayList<BotSalesEseroVO>();
		try {
			outListBotEseroVO = botSalesEseroService.listBotICHomeTaxList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListICOracleList.do")
	public @ResponseBody Map<String, Object> ListICOracleList(@RequestBody BotSalesEseroVO vo) {
		logger.info("/AjaxBot/ListICOracleList.do");
		
		List<BotSalesEseroVO> outListBotEseroVO = new ArrayList<BotSalesEseroVO>();
		try {
			outListBotEseroVO = botSalesEseroService.listBotICOracleList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListICSalesSlipList.do")
	public @ResponseBody Map<String, Object> ListICSalesSlipList(@RequestBody BotSalesEseroVO vo) {
		logger.info("/AjaxBot/ListICSalesSlipList.do");
		
		List<BotSalesEseroVO> outListBotEseroVO = new ArrayList<BotSalesEseroVO>();
		try {
			outListBotEseroVO = botSalesEseroService.listBotICSalesSlipList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListICBuySlipList.do")
	public @ResponseBody Map<String, Object> ListICBuySlipList(@RequestBody BotSalesEseroVO vo) {
		logger.info("/AjaxBot/ListICBuySlipList.do");
		
		List<BotSalesEseroVO> outListBotEseroVO = new ArrayList<BotSalesEseroVO>();
		try {
			outListBotEseroVO = botSalesEseroService.listBotICBuySlipList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListICManage.do")
	public @ResponseBody Map<String, Object> ListICManage(@RequestBody BotSalesEseroVO vo) {
		logger.info("/AjaxBot/ListICManage.do");
		
		List<BotSalesEseroVO> outListBotEseroVO = new ArrayList<BotSalesEseroVO>();
		try {
			outListBotEseroVO = botSalesEseroService.listBotICTotalList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/SalesEseroDetail.do")
	public @ResponseBody Map<String, Object> SalesEseroDetail(@RequestBody BotSalesEseroVO vo) {
		logger.info("/AjaxBot/SalesEseroDetail.do");
		
		List<BotSalesEseroVO> outListBotEseroVO = new ArrayList<BotSalesEseroVO>();
		try {
			outListBotEseroVO = botSalesEseroService.listSalesEseroDetail(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListSalesEseroManageVendorInvoiceList.do")
	public @ResponseBody Map<String, Object> ListSalesEseroManageVendorInvoiceList(@RequestBody BotSalesEseroVO vo) {
		logger.info("/AjaxBot/ListSalesEseroManageVendorInvoiceList.do");
		
		List<BotSalesEseroVO> outListBotEseroVO = new ArrayList<BotSalesEseroVO>();
		try {
			outListBotEseroVO = botSalesEseroService.listBotSalesEseroManageVendorInvoiceList(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEseroVO);
		
		return map;
	}
	
	@PostMapping("/ListSCSystemCheck.do")
	public @ResponseBody Map<String, Object> ListSCSystemCheck(@RequestBody BotSCSystemCheckVO vo) {
		logger.info("/AjaxBot/ListSCSystemCheck.do");
		
		List<BotSCSystemCheckVO> outListSCSystemCheckVO = new ArrayList<BotSCSystemCheckVO>();
		try {
			outListSCSystemCheckVO = botSCSystemCheckService.listBotSCSystemCheck(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListSCSystemCheckVO);
		
		return map;
	}
	
	@PostMapping("/ListAdDailyReport.do")
	public @ResponseBody Map<String, Object> ListAdDailyReport(@RequestBody BotAdDailyReportVO vo) {
		logger.info("/AjaxBot/ListAdDailyReport.do");
		
		List<BotAdDailyReportVO> outListAdDailyReportVO = new ArrayList<BotAdDailyReportVO>();
		try {
			outListAdDailyReportVO = botAdDailyReportService.listBotAdDailyReport(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListAdDailyReportVO);
		
		return map;
	}
	
	@PostMapping("/ListAdDailyReportTargetAd.do")
	public @ResponseBody Map<String, Object> ListAdDailyReportTargetAd(@RequestBody BotAdDailyReportVO vo) {
		logger.info("/AjaxBot/ListAdDailyReportTargetAd.do");
		
		List<BotAdDailyReportVO> outListBotAdDailyReportVO = new ArrayList<BotAdDailyReportVO>();
		try {
			outListBotAdDailyReportVO = botAdDailyReportService.listBotAdDailyReportTargetAd(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotAdDailyReportVO);
		
		return map;
	}

	@PostMapping("/ListAdDailyReportManage.do")
	public @ResponseBody Map<String, Object> ListAdDailyReportManage(@RequestBody BotAdDailyReportVO vo) {
		logger.info("/AjaxBot/ListAdDailyReportManage.do");
		
		List<BotAdDailyReportVO> outListBotAdDailyReportVO = new ArrayList<BotAdDailyReportVO>();
		try {
			outListBotAdDailyReportVO = botAdDailyReportService.listBotAdDailyReportManage(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		List<BotAdDailyReportVO> outListBotAdDailyVOChart = new ArrayList<BotAdDailyReportVO>();
		try {
			outListBotAdDailyVOChart = botAdDailyReportService.listBotAdDailyReportManageChart(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotAdDailyReportVO);
		map.put("chartData", outListBotAdDailyVOChart);
		
		return map;
	}
	
	// 세움터 사용승인(허가) 발급 체크업무
	@PostMapping("/ListEaisSchedule.do")
	public @ResponseBody Map<String, Object> ListEaisSchedule(@RequestBody BotEaisVO vo) {
		logger.info("/AjaxBot/ListEaisSchedule.do");
		
		List<BotEaisVO> outListBotEaisVO = new ArrayList<BotEaisVO>();
		try {
			outListBotEaisVO = botEaisService.listBotEaisSchedule(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEaisVO);
		
		return map;
	}
	
	@PostMapping("/SaveEaisSchedule.do")
	public @ResponseBody StatusVO SaveEaisSchedule(@RequestBody BotEaisVO[] vo) {
		logger.info("/AjaxBot/SaveEaisSchedule.do");
		String status = "Success";
		
		List<BotEaisVO> inListBotEaisVO = new ArrayList<BotEaisVO>();
		
		// json-simple 사용시 배열 파라미터 사용 가능
		for (BotEaisVO botEaisVO : vo) {
			inListBotEaisVO.add(botEaisVO);
		}
		
		try {
			botEaisService.saveEaisSchedule(inListBotEaisVO);
		} 
		catch (Exception e) {
			status = "SaveError";
			e.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	@PostMapping("/ListEais.do")
	public @ResponseBody Map<String, Object> ListEais(@RequestBody BotEaisVO vo) {
		logger.info("/AjaxBot/ListEais.do");
		
		List<BotEaisVO> outListEaisVO = new ArrayList<BotEaisVO>();
		try {
			outListEaisVO = botEaisService.listEais(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListEaisVO);
		
		return map;
	}
	
	@PostMapping("/ListInsurance.do")
	public @ResponseBody Map<String, Object> ListInsurance(@RequestBody BotInsuranceVO vo) {
		logger.info("/AjaxBot/ListInsurance.do");
		
		List<BotInsuranceVO> outListInsuranceVO = new ArrayList<BotInsuranceVO>();
		try {
			outListInsuranceVO = botInsuranceService.listInsurance(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListInsuranceVO);
		
		return map;
	}
	
	// 하도급 당초계약 키스콘 등록 업무
	@PostMapping("/ListKisconFirstConstSubcontract.do")
	public @ResponseBody Map<String, Object> ListKisconFirstConstSubcontract(@RequestBody BotKisconConstVO vo) {
		logger.info("/AjaxBot/ListKisconFirstConstSubcontract.do");
		
		List<BotKisconConstVO> outListKisconFirstConstSubcontractVO = new ArrayList<BotKisconConstVO>();
		try {
			outListKisconFirstConstSubcontractVO = botKisconConstService.listKisconFirstConstSubcontract(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListKisconFirstConstSubcontractVO);
		
		return map;
	}

	@PostMapping("/ListChgPersonalBcc.do")
	public @ResponseBody Map<String, Object> ListChgPersonalBcc(@RequestBody BotPersonalBccVO vo) {
		logger.info("/AjaxBot/ListChgPersonalBcc.do");
		
		List<BotPersonalBccVO> outListChgPersonalBccVO = new ArrayList<BotPersonalBccVO>();
		try {
			outListChgPersonalBccVO = botPersonalBccService.listChgPersonalBcc(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListChgPersonalBccVO);
		
		return map;
	}
	
	// 채불e제로 관리업무
	@PostMapping("/DelayedPaymentManage.do")
	public @ResponseBody Map<String, Object> DelayedPaymentManage(@RequestBody BotDelayedPaymentVO vo) {
		logger.info("/AjaxBot/DelayedPaymentManage.do");

		List<BotDelayedPaymentVO> outListBotDelayedPaymentManageVO = new ArrayList<BotDelayedPaymentVO>();
		try {
			outListBotDelayedPaymentManageVO = botDelayedPaymentService.listBotDelayedPaymentManage(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotDelayedPaymentManageVO);
		
		return map;
	}
	
	// 채불e제로 결과 팝업
	@PostMapping("/ListDelayedPaymentResult.do")
	public @ResponseBody Map<String, Object> ListDelayedPaymentResult(@RequestBody BotDelayedPaymentVO vo) {
		logger.info("/AjaxBot/ListDelayedPaymentResult.do");
		
		List<BotDelayedPaymentVO> outListBotDelayedPaymentManageVO = new ArrayList<BotDelayedPaymentVO>();
		
		try {
			outListBotDelayedPaymentManageVO = botDelayedPaymentService.ListDelayedPaymentResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotDelayedPaymentManageVO);
		
		return map;
	}
	
	// 
	@PostMapping("/ListXmlResult.do")
	public @ResponseBody Map<String, Object> ListXmlResult(@RequestBody BotXmlVO vo) {
		logger.info("/AjaxBot/ListXmlResult.do");
		System.out.println("test1");
		List<BotXmlVO> outListXMLVO = new ArrayList<BotXmlVO>();
		try {
			System.out.println("test2");
			outListXMLVO = botXmlService.listXmlResult(vo);
			System.out.println("test3");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListXMLVO);
		
		return map;
	}

	// 외주 견적특수조건 결과 팝업
	@PostMapping("/ListSpecialCondition.do")
	public @ResponseBody Map<String, Object> ListSpecialCondition(@RequestBody BotSpecialConditionVO vo) {
		logger.info("/AjaxBot/ListSpecialCondition.do");
		
		List<BotSpecialConditionVO> outListSpecialConditionVO = new ArrayList<BotSpecialConditionVO>();
		
		try {
			outListSpecialConditionVO = botSpecialConditionService.listSpecialCondition(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListSpecialConditionVO);
		
		return map;
	}
	
	// 서울회생법원 회생/파산 결과 팝업
	@PostMapping("/ListCorporateNoticeResult.do")
	public @ResponseBody Map<String, Object> ListCorporateNoticeResult(@RequestBody BotCorporateNoticeVO vo) {
		logger.info("/AjaxBot/ListCorporateNoticeResult.do");
		
		List<BotCorporateNoticeVO> outListBotCorporateNoticeVO = new ArrayList<BotCorporateNoticeVO>();
		
		try {
			outListBotCorporateNoticeVO = botCorporateNoticeService.listCorporateNoticeResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotCorporateNoticeVO);
		
		return map;
	}
	
	// 키스콘 참여제한업체 수집 결과 팝업
	@PostMapping("/ListCompanyRestrictionResult.do")
	public @ResponseBody Map<String, Object> ListCompanyRestrictionResult(@RequestBody BotCompanyRestrictionVO vo) {
		logger.info("/AjaxBot/ListCompanyRestrictionResult.do");

		List<BotCompanyRestrictionVO> outListBotCompanyRestrictionVO = new ArrayList<BotCompanyRestrictionVO>();
		
		try {
			outListBotCompanyRestrictionVO = botCompanyRestrictionService.listCompanyRestrictionResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotCompanyRestrictionVO);
		
		return map;
	}
	
	// 인지세 자동납부 결과 팝업
	@PostMapping("/ListContractElecStampTaxResult.do")
	public @ResponseBody Map<String, Object> ListContractElecStampTaxResult(@RequestBody BotContractElecStampTaxVO vo) {
		logger.info("/AjaxBot/ListContractElecStampTaxResult.do");

		List<BotContractElecStampTaxVO> outListBotContractElecStampTaxVO = new ArrayList<BotContractElecStampTaxVO>();
		
		try {
			outListBotContractElecStampTaxVO = botContractElecStampTaxService.listContractElecStampTaxResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotContractElecStampTaxVO);
		
		return map;
	}
	
	@PostMapping("/ListSensoryTemperatureResult.do")
	public @ResponseBody Map<String, Object> ListSensoryTemperatureResult(@RequestBody BotSensoryTemperatureVO vo) {
		logger.info("/AjaxBot/ListSensoryTemperatureResult.do");

		List<BotSensoryTemperatureVO> outListBotSensoryTemperatureVO = new ArrayList<BotSensoryTemperatureVO>();
		
		try {
			outListBotSensoryTemperatureVO = botSensoryTemperatureService.listSensoryTemperatureResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotSensoryTemperatureVO);
		
		return map;
	}
	
	// 서울회생법원 회생/파산 결과 팝업
	@PostMapping("/ListAutoCADCancelRunResult.do")
	public @ResponseBody Map<String, Object> ListAutoCADCancelRunResult(@RequestBody BotAutoCADCancelVO vo) {
		logger.info("/AjaxBot/ListAutoCADCancelRunResult.do");
		
		List<BotAutoCADCancelVO> outListBotAutoCADCancelVO = new ArrayList<BotAutoCADCancelVO>();
		
		try {
			outListBotAutoCADCancelVO =  botAutoCADCancelService.listAutoCADCancelResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotAutoCADCancelVO);
		
		return map;
	}
	
	// 법인카드 사용내역 메일 발송
	@PostMapping("/ListCorpCardSendResult.do")
	public @ResponseBody Map<String, Object> ListCorpCardSendResult(@RequestBody BotCorpCardSendVO vo) {
		logger.info("/AjaxBot/ListCorpCardSendResult.do");

		List<BotCorpCardSendVO> outListBotCompanyRestrictionVO = new ArrayList<BotCorpCardSendVO>();
		
		try {
			outListBotCompanyRestrictionVO = botCorpCardSendService.listCorpCardSendResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotCompanyRestrictionVO);
		
		return map;
	}
	
	//설계시공 등급 결과조회
	@PostMapping("/ListBotEngineerConstructionResult.do")
	public @ResponseBody Map<String, Object> ListBotEngineerConstructionResult(@RequestBody BotEngineerConstructionVO vo) {
		logger.info("/AjaxBot/ListBotEngineerConstructionResult.do");
		
		List<BotEngineerConstructionVO> outListBotEngineerConstructionResult = new ArrayList<BotEngineerConstructionVO>();
		try {
			outListBotEngineerConstructionResult = botEngineerConstructionService.listBotEngineerConstructionResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEngineerConstructionResult);
		
		return map;
	}
	
	//설계시공 등급 업무관리
	@PostMapping("/ListEngineerConstructionManage.do")
	public @ResponseBody Map<String, Object> ListEngineerConstructionManage(@RequestBody BotEngineerConstructionVO vo) {
		logger.info("/AjaxBot/ListEngineerConstructionManage.do");
		
		List<BotEngineerConstructionVO> outListBotEngineerConstructionResult = new ArrayList<BotEngineerConstructionVO>();
		try {
			outListBotEngineerConstructionResult = botEngineerConstructionService.listBotEngineerConstructionManage(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotEngineerConstructionResult);
		
		return map;
	}
	
	// 인지세 자동납부 결과 팝업
	@PostMapping("/ListStampTaxSlipDataResult.do")
	public @ResponseBody Map<String, Object> ListStampTaxSlipDataResult(@RequestBody BotStampTaxSlipDataVO vo) {
		logger.info("/AjaxBot/ListStampTaxSlipDataResult.do");

		List<BotStampTaxSlipDataVO> outListBotStampTaxSlipDataVO = new ArrayList<BotStampTaxSlipDataVO>();
		
		try {
			outListBotStampTaxSlipDataVO = botStampTaxSlipDataService.listStampTaxSlipDataResult(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListBotStampTaxSlipDataVO);
		
		return map;
	}
}

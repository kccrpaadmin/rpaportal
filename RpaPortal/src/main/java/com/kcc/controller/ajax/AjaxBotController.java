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
import com.kcc.biz.service.IBotEseroService;
import com.kcc.biz.service.IBotRequestService;
import com.kcc.biz.service.IBotScheduleService;
import com.kcc.biz.service.ICrawlScheduleService;
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
	
	@PostMapping("/RunBot.do")
	public @ResponseBody StatusVO RunBot(@RequestBody BotRequestVO vo) {
		logger.info("/AjaxBot/RunBot.do");

		// 봇 수행 (진행중인 경우 수행 안함)
		String status = botUtilService.requestBot(vo);
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
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
}

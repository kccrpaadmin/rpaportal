package com.kcc.controller.ajax;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.kcc.biz.model.AttFileVO;
import com.kcc.biz.model.FileUploadVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.OcrBusinessLicenseVO;
import com.kcc.biz.model.OcrRequestVO;
import com.kcc.biz.model.OcrTextVO;
import com.kcc.biz.model.StatusMapVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.CrawlRunVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.IAttFileService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IOcrBusinessLicenseService;
import com.kcc.biz.service.IOcrRequestService;
import com.kcc.biz.service.IOcrTextService;
import com.kcc.biz.service.ICrawlRequestService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IFileUploadUtilService;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.ICrawlUtilService;
import com.kcc.util.service.IOcrApiUtilService;
import com.kcc.util.service.IRouteUtilService;
import com.kcc.words.ConstWord;

@RequestMapping("/AjaxOcr")
@Controller
public class AjaxOcrController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxOcrController.class);

	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@Resource(name="fileUploadUtilService")
	private IFileUploadUtilService fileUploadUtilService;
	
	@Resource(name="ocrApiUtilService")
	private IOcrApiUtilService ocrApiUtilService;
	
	@Resource(name="attFileService")
	private IAttFileService attFileService;
	
	@Resource(name="ocrRequestService")
	private IOcrRequestService ocrRequestService;
	
	@Resource(name="ocrTextService")
	private IOcrTextService ocrTextService;
	
	@Resource(name="ocrBusinessLicenseService")
	private IOcrBusinessLicenseService ocrBusinessLicenseService;	
	
	@PostMapping("/UploadFiles.do")
	public @ResponseBody StatusVO UploadFiles(FileUploadVO fvo, OcrRequestVO vo) {
		logger.info("/AjaxOcr/UploadFiles.do");
		String status = "Success";
		String errorMsg = "";
		String attId = "";
		
		try {
			// 첨부파일 저장
			attId = fileUploadUtilService.createFiles(fvo.getFiles(), vo.getMenuId(), vo.getEmpNo());
		}
		catch (Exception e1) {
			status = "FileSaveError";
			errorMsg = e1.getMessage();
			e1.printStackTrace();
		}		
		
		// 첨부파일 저장이 성공한 경우
		if (status.equals("Success")) {
			vo.setAttId(attId);
			
			try {
				ocrRequestService.createOcrRequest(vo);
			} 
			catch (Exception e2) {
				status = "RequestSaveError";
				errorMsg = e2.getMessage();
				e2.printStackTrace();
			}
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		statusVO.setErrorMsg(errorMsg);
		
    	return statusVO;
	}
	
	@PostMapping("/ListRequest.do")
	public @ResponseBody Map<String, Object> ListRequest(@RequestBody OcrRequestVO vo) {
		logger.info("/AjaxOcr/ListRequest.do");
		
		List<OcrRequestVO> outListOcrRequestVO = new ArrayList<OcrRequestVO>();
		try {
			outListOcrRequestVO = ocrRequestService.listOcrRequest(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outListOcrRequestVO);
		
		return map;
	}
	
	@PostMapping("/ListText.do")
	public @ResponseBody Map<String, Object> ListText(@RequestBody OcrTextVO vo) {
		logger.info("/AjaxOcr/ListText.do");
		
		List<OcrTextVO> outOcrTextVO = new ArrayList<OcrTextVO>();
		try {
			outOcrTextVO = ocrTextService.listOcrText(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outOcrTextVO);
		
		return map;
	}
	
	@PostMapping("/GetText.do")
	public @ResponseBody OcrTextVO GetText(@RequestBody OcrTextVO vo) {
		logger.info("/AjaxOcr/GetText.do");
		
		OcrTextVO outOcrTextVO = new OcrTextVO();
		try {
			outOcrTextVO = ocrTextService.getOcrText(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return outOcrTextVO;
	}
	
	@PostMapping("/RunOcrText.do")
	public @ResponseBody StatusVO RunOcrText(@RequestBody OcrRequestVO vo) {
		logger.info("/AjaxOcr/RunOcrText.do");
		String status = "Success";
				
		List<OcrRequestVO> outListOcrRequestVO = new ArrayList<OcrRequestVO>();
		try {
			outListOcrRequestVO = ocrRequestService.listOcrRequestAttFile(vo);
		} 
		catch (Exception e) {
			status = "ListSearchError";
			e.printStackTrace();
		}
		
		// 목록이 조회된 경우
		if (status.equals("Success")) {
			for (OcrRequestVO ocrRequestVO : outListOcrRequestVO) {
				// Ocr 처리 요청
				StatusVO statusVO = ocrApiUtilService.requestOcrVision(ocrRequestVO.getFullFilePath());
				
				// OcrTextVO 입력 
				OcrTextVO inOcrTextVO = new OcrTextVO();
				inOcrTextVO.setRequestNo(ocrRequestVO.getRequestNo());
				inOcrTextVO.setSeq(ocrRequestVO.getSeq());
				inOcrTextVO.setOcrData(statusVO.getData());
				inOcrTextVO.setErrorMsg(statusVO.getErrorMsg());
				inOcrTextVO.setEmpNo(vo.getEmpNo());
				
				if (statusVO.getStatus().equals("Success")) {
					try {
						inOcrTextVO.setActionCd("RA005001");
						ocrTextService.createOcrText(inOcrTextVO);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						inOcrTextVO.setActionCd("RA005002");
						ocrTextService.createOcrText(inOcrTextVO);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}				
			}
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	@PostMapping("/ListBusinessLicense.do")
	public @ResponseBody Map<String, Object> ListBusinessLicense(@RequestBody OcrBusinessLicenseVO vo) {
		logger.info("/AjaxOcr/ListBusinessLicense.do");
		
		List<OcrBusinessLicenseVO> outOcrBusinessLicenseVO = new ArrayList<OcrBusinessLicenseVO>();
		try {
			outOcrBusinessLicenseVO = ocrBusinessLicenseService.listOcrBusinessLicense(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", outOcrBusinessLicenseVO);
		
		return map;
	}
	
	@PostMapping("/GetBusinessLicense.do")
	public @ResponseBody OcrBusinessLicenseVO GetBusinessLicense(@RequestBody OcrBusinessLicenseVO vo) {
		logger.info("/AjaxOcr/GetBusinessLicense.do");
		
		OcrBusinessLicenseVO outOcrBusinessLicenseVO = new OcrBusinessLicenseVO();
		try {
			outOcrBusinessLicenseVO = ocrBusinessLicenseService.getOcrBusinessLicense(vo);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return outOcrBusinessLicenseVO;
	}
	
	@PostMapping("/RunOcrBusinessLicense.do")
	public @ResponseBody StatusVO RunOcrBusinessLicense(@RequestBody OcrRequestVO vo) {
		logger.info("/AjaxOcr/RunOcrBusinessLicense.do");
		String status = "Success";
				
		List<OcrRequestVO> outListOcrRequestVO = new ArrayList<OcrRequestVO>();
		try {
			outListOcrRequestVO = ocrRequestService.listOcrRequestAttFile(vo);
		} 
		catch (Exception e) {
			status = "ListSearchError";
			e.printStackTrace();
		}
		
		// 목록이 조회된 경우
		if (status.equals("Success")) {
			for (OcrRequestVO ocrRequestVO : outListOcrRequestVO) {
				// Ocr 처리 요청
				StatusMapVO statusMapVO = ocrApiUtilService.requestOcrNaverTemp(vo.getMenuId(), ocrRequestVO.getFullFilePath().toString());
				
				// OcrBusinessLicenseVO 입력 
				OcrBusinessLicenseVO ocrBusinessLicenseVO = new OcrBusinessLicenseVO();
				ocrBusinessLicenseVO.setRequestNo(ocrRequestVO.getRequestNo());
				ocrBusinessLicenseVO.setSeq(ocrRequestVO.getSeq());
				ocrBusinessLicenseVO.setErrorMsg(statusMapVO.getErrorMsg());
				ocrBusinessLicenseVO.setEmpNo(vo.getEmpNo());
				
				if (statusMapVO.getStatus().equals("Success")) {
					try {
						ocrBusinessLicenseVO.setActionCd("RA005001");
						Map mapData = statusMapVO.getMapData();
						Iterator<String> iter = mapData.keySet().iterator();
						while (iter.hasNext()) {
							String key = iter.next();
							String value = (String) mapData.get(key);
							Field idField = OcrBusinessLicenseVO.class.getDeclaredField(key);
							idField.setAccessible(true);
							idField.set(ocrBusinessLicenseVO, value);
		            	}
						ocrBusinessLicenseService.createOcrBusinessLicense(ocrBusinessLicenseVO);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						ocrBusinessLicenseVO.setActionCd("RA005002");
						ocrBusinessLicenseVO.setTemplateId("");
						ocrBusinessLicenseVO.setTemplateNm("");
						ocrBusinessLicenseVO.setBizNo("");
						ocrBusinessLicenseVO.setVendorNm("");
						ocrBusinessLicenseVO.setRepNm("");
						ocrBusinessLicenseVO.setCorpNo("");
						ocrBusinessLicenseVO.setAddr("");
						ocrBusinessLicenseVO.setBizType("");
						ocrBusinessLicenseVO.setBizKind("");
						ocrBusinessLicenseService.createOcrBusinessLicense(ocrBusinessLicenseVO);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}				
			}
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		
		return statusVO;
	}
	
	
}

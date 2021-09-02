package com.kcc.controller.menu;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.FileUploadVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.OcrRequestVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.model.ProposalVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;
import com.kcc.biz.service.IProposalService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.IRouteUtilService;
import com.kcc.util.service.IFileUploadUtilService;

@RequestMapping("/Proposal")
@Controller
public class ProposalController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ProposalController.class);
	
	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@Resource(name="proposalService")
	private IProposalService proposalService;
	
	@Resource(name="fileUploadUtilService")
	private IFileUploadUtilService fileUploadUtilService;
	
	
	@GetMapping("/ListProposal.do")
	public String ListProposal() {
		logger.info("/Proposal/ListProposal.do");
		return "Proposal/ListProposal";
	}
	
	@GetMapping("ProposalDetail.do")
	public String ProposalDetail(String pProposalNo, String pMode, Model model) {
		logger.info("/Proposal/ProposalDetail.do");
		
		// ProposalVO 입력
		ProposalVO inProposalVO = new ProposalVO();
		inProposalVO.setProposalNo(pProposalNo);
		inProposalVO.setMode(pMode);
		
		// ProposalVO 출력
		ProposalVO outProposalVO = new ProposalVO();
		
		try {
			// 과제 건의 정보 상세 조회
			outProposalVO = proposalService.getProposalDetail(inProposalVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outProposalVO", outProposalVO);
		
		return "Proposal/ProposalDetail";
	}
	
	@GetMapping("ProposalWrite.do")
	public String ProposalWrite(String pProposalNo, String pMode, Model model) {
		logger.info("/Proposal/ProposalWrite.do");

		// ProposalVO 입력
		ProposalVO inProposalVO = new ProposalVO();
		inProposalVO.setProposalNo(pProposalNo);
		inProposalVO.setMode(pMode);
		
		// ProposalVO 출력
		ProposalVO outProposalVO = new ProposalVO();
		
		try {
			// 과제 건의 정보 상세 조회
			outProposalVO = proposalService.getProposalDetail(inProposalVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("menuCdComboBox", commonUtilService.getCodeProcedureSelectBox("menuId", "PRA_Proposal_listProposalMenuCombo", "", true, "", ""));
		model.addAttribute("outProposalVO", outProposalVO);
		model.addAttribute("fileListBoxAttId", fileUploadUtilService.createFileControl("첨부파일", "AttId", commonUtilService.isEmptyCheck(outProposalVO) ? "" :  outProposalVO.getAttId(), true, "Left", "49%"));
		
		return "Proposal/ProposalWrite";
	}
	
	@PostMapping("/ProposalWrite.do")
	public String ProposalWrite(FileUploadVO fvo, ProposalVO vo) {
		logger.info("/Proposal/ProposalWrite.do");
		logger.info(vo.getProposalNo());
		String status = "Fail";
		String errorMsg = "";
		String attId = "";
		
		try {
			// 첨부파일 저장
			attId = fileUploadUtilService.createFiles(fvo.getFiles(), "Proposal", vo.getEmpNo());
			status = "Success";
		}
		catch (Exception e1) {
			status = "FileSaveError";
			errorMsg = e1.getMessage();
			e1.printStackTrace();
		}		
		
		// 첨부파일 저장이 성공한 경우
		if (status.equals("Success")) {
			vo.setAttId(attId);
			
			logger.info(attId);
			
			try {
				proposalService.createProposalWrite(vo);
			} 
			catch (Exception e2) {
				status = "SaveError";
				errorMsg = e2.getMessage();
				e2.printStackTrace();
			}
		}
					
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		statusVO.setErrorMsg(errorMsg);
		
		return "Proposal/ListProposal";
	}
		
	@GetMapping("ProposalReview.do")
	public String ProposalReview(String pProposalNo, String pMode, Model model) {
		logger.info("/Proposal/ProposalReview.do");

		// ProposalVO 입력
		ProposalVO inProposalVO = new ProposalVO();
		inProposalVO.setProposalNo(pProposalNo);
		inProposalVO.setMode(pMode);
		
		// ProposalVO 출력
		ProposalVO outProposalVO = new ProposalVO();
		
		try {
			// 과제 건의 정보 상세 조회
			outProposalVO = proposalService.getProposalDetail(inProposalVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
			
		model.addAttribute("statusCdComboBox", commonUtilService.getCodeProcedureSelectBox("status_cd", "PRA_Proposal_listProposalStatusCombo", "", true, "", ""));
		model.addAttribute("outProposalVO", outProposalVO);
		
		return "Proposal/ProposalReview";
	}
	
}

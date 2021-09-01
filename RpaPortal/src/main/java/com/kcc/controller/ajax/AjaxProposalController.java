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
import com.kcc.biz.model.BotEseroVO;
import com.kcc.biz.model.ProposalVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.biz.model.AttFileVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.IProposalService;
import com.kcc.biz.service.ILoginService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;
import com.kcc.biz.service.IAttFileService;

@RequestMapping("/AjaxProposal")
@Controller
public class AjaxProposalController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxProposalController.class);
	
	@Resource(name="proposalService")
	private IProposalService proposalService;
	
	@Resource(name="attFileService")
	private IAttFileService attFileService;
	
	@PostMapping("/ListProposal.do")
	public @ResponseBody Map<String, Object> ListProposal(@RequestBody ProposalVO vo) {
		logger.info("/AjaxProposal/ListProposal.do");
		
		List<ProposalVO> listProposalVO = new ArrayList<ProposalVO>();
		try {
			
			listProposalVO = proposalService.listProposal(vo);			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", listProposalVO);
		
		return map;
	}		
	
	//	과제 건의 생성
	@PostMapping("/CreateProposalWrite.do")
	public @ResponseBody StatusVO CreateProposalWrite(@RequestBody ProposalVO vo) {
			logger.info("/AjaxProposal/CreateProposalWrite.do");
			String status = "Success";
			
			try {
				proposalService.createProposalWrite(vo);
			} 
			catch (Exception e) {
				status = "SaveError";
				e.printStackTrace();
			}
			
			StatusVO statusVO = new StatusVO();
			statusVO.setStatus(status);
			
			return statusVO;
	}
	
    //	과제 건의 수정
	@PostMapping("/UpdateProposalWrite.do")
	public @ResponseBody StatusVO UpdateProposalWrite(@RequestBody ProposalVO vo) {
			logger.info("/AjaxProposal/UpdateProposalWrite.do");
			String status = "Success";
			
			try {
				proposalService.updateProposalWrite(vo);
			} 
			catch (Exception e) {
				status = "SaveError";
				e.printStackTrace();
			}
			
			StatusVO statusVO = new StatusVO();
			statusVO.setStatus(status);
			
			return statusVO;
	 }
	
    //	과제 건의 삭제
	@PostMapping("/DeleteProposal.do")
	public @ResponseBody StatusVO DeleteProposal(@RequestBody ProposalVO vo) {
			logger.info("/AjaxProposal/DeleteProposal.do");
			String status = "Success";
			
			try {
				proposalService.deleteProposal(vo);
			} 
			catch (Exception e) {
				status = "SaveError";
				e.printStackTrace();
			}
			
			StatusVO statusVO = new StatusVO();
			statusVO.setStatus(status);
			
			return statusVO;
	 }
	
     //	검토의견 작성
	@PostMapping("/SaveProposalReview.do")
	public @ResponseBody StatusVO SaveProposalReview(@RequestBody ProposalVO vo) {
			logger.info("/AjaxProposal/SaveProposalReview.do");
			String status = "Success";
			
			try {
				proposalService.saveProposalReview(vo);
			} 
			catch (Exception e) {
				status = "SaveError";
				e.printStackTrace();
			}
			
			StatusVO statusVO = new StatusVO();
			statusVO.setStatus(status);
			
			return statusVO;
	 }
	
	// 첨부파일 목록 조회
	@PostMapping("/ListAttFile.do")
	public @ResponseBody Map<String, Object> ListAttFile(@RequestBody AttFileVO vo) {
		logger.info("/AjaxProposal/ListAttFile.do");
		
		List<AttFileVO> listAttFileVO = new ArrayList<AttFileVO>();
		try {
			
			listAttFileVO = attFileService.listAttFile(vo);			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map map = new HashMap<String, Object>();
		map.put("data", listAttFileVO);
		
		return map;
	}	
}

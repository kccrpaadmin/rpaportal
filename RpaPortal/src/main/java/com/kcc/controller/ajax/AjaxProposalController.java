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
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.IProposalService;
import com.kcc.biz.service.ILoginService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/AjaxProposal")
@Controller
public class AjaxProposalController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxProposalController.class);
	
	@Resource(name="proposalService")
	private IProposalService proposalService;
	
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
	
	//	과제 건의 저장
	@PostMapping("/SaveProposalWrite.do")
	public @ResponseBody StatusVO SaveProposalWrite(@RequestBody ProposalVO vo) {
			logger.info("/AjaxProposal/SaveProposalWrite.do");
			String status = "Success";
			
			try {
				proposalService.saveProposalWrite(vo);
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

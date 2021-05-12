package com.kcc.controller.common;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import javax.xml.ws.soap.AddressingFeature.Responses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Common")
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@GetMapping("/MessageAndMove.do")
	public String MessageAndMove(@RequestParam(value="pMsg", required=false) String pMsg, @RequestParam(value="pReturnPage", required=false) String pReturnPage, Model model) {
		logger.info("/Common/MessageAndMove.do");
		
		model.addAttribute("pMsg", pMsg);
		model.addAttribute("pReturnPage", pReturnPage);
        
        return "Common/MessageAndMove";
    }
	
	@GetMapping("/AccessDenied.do")
	public String AccessDenied(HttpServletResponse res) {
		logger.info("/Common/AccessDenied.do");
		
		res.setStatus(200);
		return "Common/AccessDenied";
	}
	
	@GetMapping("/ParameterError.do")
	public String ParameterError(HttpServletResponse res) {
		logger.info("/Common/ParameterError.do");

		return "Common/ParameterError";
	}
	
	@GetMapping("/PageNotFound.do")
	public String PageNotFound(HttpServletResponse res) {
		logger.info("/Common/PageNotFound.do");

		return "Common/PageNotFound";
	}
	
	@GetMapping("/ProgramingError.do")
	public String ProgramingError(HttpServletResponse res) {
		logger.info("/Common/ProgramingError.do");

		return "Common/ProgramingError";
	}
}
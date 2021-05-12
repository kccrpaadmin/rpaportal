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

import com.kcc.biz.model.BotRequestVO;
import com.kcc.biz.service.IBotRequestService;
import com.kcc.controller.base.BaseController;

@RequestMapping("/AjaxBot")
@Controller
public class AjaxBotController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxBotController.class);

	@Resource(name="botRequestService")
	private IBotRequestService botRequestService;
		
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
}

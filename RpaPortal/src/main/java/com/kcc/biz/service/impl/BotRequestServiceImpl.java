package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEseroRepository;
import com.kcc.biz.dao.BotRequestRepository;
import com.kcc.biz.dao.CrawlRequestRepository;
import com.kcc.biz.model.BotEseroVO;
import com.kcc.biz.model.BotRequestVO;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.service.IBotEseroService;
import com.kcc.biz.service.IBotRequestService;
import com.kcc.biz.service.ICrawlRequestService;

@Service("botRequestService")
public class BotRequestServiceImpl implements IBotRequestService {
	private static final Logger logger = LoggerFactory.getLogger(BotRequestServiceImpl.class);
	
	@Resource(name="botRequestRepository")
	private BotRequestRepository botRequestRepository;
	
	public void createBotRequest(BotRequestVO vo) throws Exception {
		botRequestRepository.createBotRequest(vo);
	}
	
	public void updateBotRequest(BotRequestVO vo) throws Exception {
		botRequestRepository.updateBotRequest(vo);
	}
	
	public List<BotRequestVO> listBotRequest(BotRequestVO vo) throws Exception {
		return botRequestRepository.listBotRequest(vo);
	}
	
	public BotRequestVO getBotRequestStatus(BotRequestVO vo) throws Exception {
		return botRequestRepository.getBotRequestStatus(vo);
	}		
}

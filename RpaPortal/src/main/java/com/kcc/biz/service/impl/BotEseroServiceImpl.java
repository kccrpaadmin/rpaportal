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

@Service("botEseroService")
public class BotEseroServiceImpl implements IBotEseroService {
	private static final Logger logger = LoggerFactory.getLogger(BotEseroServiceImpl.class);
	
	@Resource(name="botEseroRepository")
	private BotEseroRepository botEseroRepository;
		
	public List<BotEseroVO> listBotEseroTargetDate(BotEseroVO vo) throws Exception {
		return botEseroRepository.listBotEseroTargetDate(vo);
	}
}

package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotBidChangeRepository;
import com.kcc.biz.model.BotBidChangeVO;
import com.kcc.biz.service.IBotBidChangeService;

@Service("botBidChangeService")
public class BotBidChangeServiceImpl implements IBotBidChangeService {
	private static final Logger logger = LoggerFactory.getLogger(BotBidChangeServiceImpl.class);
	
	@Resource(name="botBidChangeRepository")
	private BotBidChangeRepository botBidChangeRepository;
			
	public List<BotBidChangeVO> listBotBidChangeG2b(BotBidChangeVO vo) throws Exception {		
		return botBidChangeRepository.listBotBidChangeG2b(vo);
	}	
}

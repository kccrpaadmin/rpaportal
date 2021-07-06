package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotMoneySendRepository;
import com.kcc.biz.model.BotMoneySendVO;
import com.kcc.biz.service.IBotMoneySendService;

@Service("botMoneySendService")
public class BotMoneySendServiceImpl implements IBotMoneySendService {
	private static final Logger logger = LoggerFactory.getLogger(BotMoneySendServiceImpl.class);
	
	@Resource(name="botMoneySendRepository")
	private BotMoneySendRepository botMoneySendRepository;
	
	public void saveBotMoneySend(BotMoneySendVO vo) throws Exception {
		botMoneySendRepository.saveBotMoneySend(vo);
	}
	
	public List<BotMoneySendVO> listBotMoneySend(BotMoneySendVO vo) throws Exception {
		return botMoneySendRepository.listBotMoneySend(vo);
	}
}

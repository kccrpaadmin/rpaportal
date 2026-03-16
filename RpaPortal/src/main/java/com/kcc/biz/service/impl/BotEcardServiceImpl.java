package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEcardRepository;
import com.kcc.biz.model.BotEcardVO;
import com.kcc.biz.service.IBotEcardService;

@Service("botEcardService")
public class BotEcardServiceImpl implements IBotEcardService {
	private static final Logger logger = LoggerFactory.getLogger(BotEcardServiceImpl.class);
	
	@Resource(name="botEcardRepository")
	private BotEcardRepository botEcardRepository;
	
	public void createBotEcardRequestDate(BotEcardVO vo) throws Exception {
		botEcardRepository.createBotEcardRequestDate(vo);
	}
	
	public List<BotEcardVO> listBotEcardTargetDate(BotEcardVO vo) throws Exception {
		return botEcardRepository.listBotEcardTargetDate(vo);
	}
}

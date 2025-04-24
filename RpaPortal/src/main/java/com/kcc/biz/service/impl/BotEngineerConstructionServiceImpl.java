package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEngineerConstructionRepository;
import com.kcc.biz.model.BotEngineerConstructionVO;
import com.kcc.biz.service.IBotEngineerConstructionService;

@Service("botEngineerConstructionService")
public class BotEngineerConstructionServiceImpl implements IBotEngineerConstructionService {
	private static final Logger logger = LoggerFactory.getLogger(BotEngineerConstructionServiceImpl.class);
	
	@Resource(name="botEngineerConstructionRepository")
	private BotEngineerConstructionRepository botEngineerConstructionRepository;
		
	public List<BotEngineerConstructionVO> listBotEngineerConstructionManage(BotEngineerConstructionVO vo) throws Exception {		
		return botEngineerConstructionRepository.listBotEngineerConstructionManage(vo);
	}
	
	public List<BotEngineerConstructionVO> listBotEngineerConstructionResult(BotEngineerConstructionVO vo) throws Exception {		
		return botEngineerConstructionRepository.listBotEngineerConstructionResult(vo);
	}
}

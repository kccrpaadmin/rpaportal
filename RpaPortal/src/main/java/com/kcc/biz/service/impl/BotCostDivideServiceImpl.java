package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotCostDivideRepository;
import com.kcc.biz.model.BotCostDivideVO;
import com.kcc.biz.service.IBotCostDivideService;

@Service("botCostDivideService")
public class BotCostDivideServiceImpl implements IBotCostDivideService {
	private static final Logger logger = LoggerFactory.getLogger(BotCostDivideServiceImpl.class);
	
	@Resource(name="botCostDivideRepository")
	private BotCostDivideRepository botCostDivideRepository;
	
	public void createBotCostDivideTargetDate(BotCostDivideVO vo) throws Exception {
		botCostDivideRepository.createBotCostDivideTargetDate(vo);
	}
	
	public List<BotCostDivideVO> listBotCostDivideTargetDate(BotCostDivideVO vo) throws Exception {
		return botCostDivideRepository.listBotCostDivideTargetDate(vo);
	}
}

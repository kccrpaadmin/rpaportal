package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotSpecialConditionRepository;
import com.kcc.biz.model.BotSpecialConditionVO;
import com.kcc.biz.service.IBotSpecialConditionService;

@Service("botSpecialConditionService")
public class BotSpecialConditionServiceImpl implements IBotSpecialConditionService {
	private static final Logger logger = LoggerFactory.getLogger(BotSpecialConditionServiceImpl.class);
	
	@Resource(name="botSpecialConditionRepository")
	private BotSpecialConditionRepository botSpecialConditionRepository;
	
	public List<BotSpecialConditionVO> listSpecialCondition(BotSpecialConditionVO vo) throws Exception {
		return botSpecialConditionRepository.listSpecialCondition(vo);
	}
}

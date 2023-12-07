package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEaisRepository;
import com.kcc.biz.model.BotEaisVO;
import com.kcc.biz.service.IBotEaisService;

@Service("botEaisService")
public class BotEaisServiceImpl implements IBotEaisService {
	private static final Logger logger = LoggerFactory.getLogger(BotEaisServiceImpl.class);
	
	@Resource(name="botEaisRepository")
	private BotEaisRepository botEaisRepository;
	
	public List<BotEaisVO> listBotEaisSchedule(BotEaisVO vo) throws Exception {
		return botEaisRepository.listBotEaisSchedule(vo);
	}
	
	public void saveEaisSchedule(List<BotEaisVO> vo) throws Exception {
		for (BotEaisVO botEaisVO : vo) {
			botEaisRepository.saveEaisSchedule(botEaisVO);
		}
	}
}

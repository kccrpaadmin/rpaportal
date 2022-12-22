package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotPersonalBccRepository;
import com.kcc.biz.model.BotPersonalBccVO;
import com.kcc.biz.service.IBotPersonalBccService;

@Service("botPersonalBccService")
public class BotPersonalBccServiceImpl implements IBotPersonalBccService {
	private static final Logger logger = LoggerFactory.getLogger(BotPersonalBccServiceImpl.class);
	
	@Resource(name="botPersonalBccRepository")
	private BotPersonalBccRepository botPersonalBccRepository;
	
	public List<BotPersonalBccVO> listBotPersonalBcc(BotPersonalBccVO vo) throws Exception {		
		return botPersonalBccRepository.listBotPersonalBcc(vo);
	}
}

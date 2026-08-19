package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotSystemCheckRepository;
import com.kcc.biz.model.BotSystemCheckVO;
import com.kcc.biz.service.IBotSystemCheckService;


@Service("botSystemChcekService")
public class BotSystemCheckServiceImpl implements IBotSystemCheckService {
	private static final Logger logger = LoggerFactory.getLogger(BotSystemCheckServiceImpl.class);
	
	@Resource(name="botSystemCheckRepository")
	private BotSystemCheckRepository botSystemCheckRepository;
	
	public List<BotSystemCheckVO> listBotSystemCheckResult(BotSystemCheckVO vo) throws Exception {
		return botSystemCheckRepository.listBotSystemCheckResult(vo);
	}	
}

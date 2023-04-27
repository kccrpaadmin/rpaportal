package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotSCSystemCheckRepository;
import com.kcc.biz.model.BotSCSystemCheckVO;
import com.kcc.biz.service.IBotSCSystemCheckService;

@Service("botSCSystemCheckService")
public class BotSCSystemCheckServiceImpl implements IBotSCSystemCheckService {
	private static final Logger logger = LoggerFactory.getLogger(BotSCSystemCheckServiceImpl.class);
	
	@Resource(name="botSCSystemCheckRepository")
	private BotSCSystemCheckRepository botSCSystemCheckRepository;
	
	public List<BotSCSystemCheckVO> listBotSCSystemCheck(BotSCSystemCheckVO vo) throws Exception {
		return botSCSystemCheckRepository.listBotSCSystemCheck(vo);
	}	
}

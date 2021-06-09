package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.model.BotScheduleVO;
import com.kcc.biz.dao.BotScheduleRepository;
import com.kcc.biz.service.IBotScheduleService;

@Service("botScheduleService")
public class BotScheduleServiceImpl implements IBotScheduleService {
	private static final Logger logger = LoggerFactory.getLogger(BotScheduleServiceImpl.class);
	
	@Resource(name="botScheduleRepository")
	private BotScheduleRepository botScheduleRepository;

	public void createBotSchedule(BotScheduleVO vo) throws Exception {
		botScheduleRepository.createBotSchedule(vo);
	}
	
	public void deleteBotSchedule(BotScheduleVO vo) throws Exception {
		botScheduleRepository.deleteBotSchedule(vo);
	}
	
	public List<BotScheduleVO> listBotSchedule(BotScheduleVO vo) throws Exception {
		return botScheduleRepository.listBotSchedule(vo);
	}
	
	public List<BotScheduleVO> listBotScheduleMenu(BotScheduleVO vo) throws Exception {
		return botScheduleRepository.listBotScheduleMenu(vo);
	}
}

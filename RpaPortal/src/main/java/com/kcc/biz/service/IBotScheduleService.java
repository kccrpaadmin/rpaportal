package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotScheduleVO;

public interface IBotScheduleService {
	void createBotSchedule(BotScheduleVO vo) throws Exception;
	void deleteBotSchedule(BotScheduleVO vo) throws Exception;
	List<BotScheduleVO> listBotSchedule(BotScheduleVO vo) throws Exception;
	List<BotScheduleVO> listBotScheduleMenu(BotScheduleVO vo) throws Exception;
}

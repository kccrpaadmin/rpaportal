package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotScheduleVO;

@Repository("botScheduleRepository")
public interface BotScheduleRepository {
	void createBotSchedule(BotScheduleVO vo) throws Exception;
	void deleteBotSchedule(BotScheduleVO vo) throws Exception;
	List<BotScheduleVO> listBotSchedule(BotScheduleVO vo) throws Exception;
	List<BotScheduleVO> listBotScheduleMenu(BotScheduleVO vo) throws Exception;
}

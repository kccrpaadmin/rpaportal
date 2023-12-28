package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotEaisVO;

@Repository("botEaisRepository")
public interface BotEaisRepository {
	List<BotEaisVO> listBotEaisSchedule(BotEaisVO vo) throws Exception;
	void saveEaisSchedule(BotEaisVO vo) throws Exception;	
	List<BotEaisVO> listEais(BotEaisVO vo) throws Exception;
}

package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotEngineerConstructionVO;

@Repository("botEngineerConstructionRepository")
public interface BotEngineerConstructionRepository {	
	List<BotEngineerConstructionVO> listBotEngineerConstructionManage(BotEngineerConstructionVO vo) throws Exception;
	
	List<BotEngineerConstructionVO> listBotEngineerConstructionResult(BotEngineerConstructionVO vo) throws Exception;
}

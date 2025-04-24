package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEngineerConstructionVO;

public interface IBotEngineerConstructionService {	
	List<BotEngineerConstructionVO> listBotEngineerConstructionManage(BotEngineerConstructionVO vo) throws Exception;
	
	List<BotEngineerConstructionVO> listBotEngineerConstructionResult(BotEngineerConstructionVO vo) throws Exception;
}
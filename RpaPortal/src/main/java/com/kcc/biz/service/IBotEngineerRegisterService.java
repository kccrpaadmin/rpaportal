package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEngineerRegisterVO;

public interface IBotEngineerRegisterService {	
	List<BotEngineerRegisterVO> listBotEngineerRegisterResult(BotEngineerRegisterVO vo) throws Exception;
}
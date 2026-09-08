package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEcardVO;
import com.kcc.biz.model.BotLaborVO;

public interface IBotEcardService {
	void createBotEcardRequestDate(BotEcardVO vo) throws Exception;
	List<BotEcardVO> listBotEcardTargetDate(BotEcardVO vo) throws Exception;
	List<BotLaborVO> listBotLaborTargetDate(BotLaborVO vo) throws Exception;
}
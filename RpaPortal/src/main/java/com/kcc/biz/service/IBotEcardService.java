package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEcardVO;

public interface IBotEcardService {
	void createBotEcardRequestDate(BotEcardVO vo) throws Exception;
	List<BotEcardVO> listBotEcardTargetDate(BotEcardVO vo) throws Exception;
}
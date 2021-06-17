package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEseroVO;

public interface IBotEseroService {
	void saveBotEseroTargetDate(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroTargetDate(BotEseroVO vo) throws Exception;
}
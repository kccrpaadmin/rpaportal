package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotMoneySendVO;

public interface IBotMoneySendService {
	void saveBotMoneySend(BotMoneySendVO vo) throws Exception;
	List<BotMoneySendVO> listBotMoneySend(BotMoneySendVO vo) throws Exception;
}
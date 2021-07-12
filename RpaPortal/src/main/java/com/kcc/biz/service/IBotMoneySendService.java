package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotMoneySendVO;

public interface IBotMoneySendService {	
	List<BotMoneySendVO> listBotMoneySendList(BotMoneySendVO vo) throws Exception;
}
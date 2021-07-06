package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotMoneySendVO;

@Repository("botMoneySendRepository")
public interface BotMoneySendRepository {
	void saveBotMoneySend(BotMoneySendVO vo) throws Exception;
	List<BotMoneySendVO> listBotMoneySend(BotMoneySendVO vo) throws Exception;
}

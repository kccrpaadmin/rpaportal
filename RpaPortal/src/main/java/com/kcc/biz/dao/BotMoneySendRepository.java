package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotMoneySendVO;

@Repository("botMoneySendRepository")
public interface BotMoneySendRepository {	
	List<BotMoneySendVO> listBotMoneySendList(BotMoneySendVO vo) throws Exception;
}

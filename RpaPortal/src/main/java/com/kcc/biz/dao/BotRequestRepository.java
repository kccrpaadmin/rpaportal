package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotRequestVO;

@Repository("botRequestRepository")
public interface BotRequestRepository {
	void createBotRequest(BotRequestVO vo) throws Exception;
	void updateBotRequest(BotRequestVO vo) throws Exception;
	List<BotRequestVO> listBotRequest(BotRequestVO vo) throws Exception;
	BotRequestVO getBotRequestStatus(BotRequestVO vo) throws Exception;
}

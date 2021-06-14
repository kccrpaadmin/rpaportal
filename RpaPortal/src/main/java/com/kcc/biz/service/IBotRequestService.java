package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotRequestVO;

public interface IBotRequestService {
	void createBotRequest(BotRequestVO vo) throws Exception;
	void updateBotRequest(BotRequestVO vo) throws Exception;
	List<BotRequestVO> listBotRequest(BotRequestVO vo) throws Exception;
	BotRequestVO getBotRequestStatus(BotRequestVO vo) throws Exception;
}
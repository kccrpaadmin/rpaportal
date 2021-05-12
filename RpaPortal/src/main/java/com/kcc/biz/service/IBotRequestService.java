package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotRequestVO;

public interface IBotRequestService {
	List<BotRequestVO> listBotRequest(BotRequestVO vo) throws Exception;
}
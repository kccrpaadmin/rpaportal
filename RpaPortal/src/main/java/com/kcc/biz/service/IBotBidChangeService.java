package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotBidChangeVO;

public interface IBotBidChangeService {	
	List<BotBidChangeVO> listBotBidChangeG2b(BotBidChangeVO vo) throws Exception;	
	List<BotBidChangeVO> listBotBidChangeTargetBid(BotBidChangeVO vo) throws Exception;	
	void createBotBidChangeTargetBid(List<BotBidChangeVO> vo) throws Exception;
	void deleteBotBidChangeTargetBid(List<BotBidChangeVO> vo) throws Exception;
}
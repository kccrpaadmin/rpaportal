package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotBidChangeVO;

@Repository("botBidChangeRepository")
public interface BotBidChangeRepository {	
	List<BotBidChangeVO> listBotBidChangeG2b(BotBidChangeVO vo) throws Exception;	
}

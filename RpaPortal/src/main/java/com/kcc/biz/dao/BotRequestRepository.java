package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotRequestVO;

@Repository("botRequestRepository")
public interface BotRequestRepository {
	List<BotRequestVO> listBotRequest(BotRequestVO vo) throws Exception;
}

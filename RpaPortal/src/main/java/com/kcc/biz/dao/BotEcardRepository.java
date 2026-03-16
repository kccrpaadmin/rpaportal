package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotEcardVO;

@Repository("botEcardRepository")
public interface BotEcardRepository {
	void createBotEcardRequestDate(BotEcardVO vo) throws Exception;
	List<BotEcardVO> listBotEcardTargetDate(BotEcardVO vo) throws Exception;
}

package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotCostDivideVO;

@Repository("botCostDivideRepository")
public interface BotCostDivideRepository {
	void createBotCostDivideTargetDate(BotCostDivideVO vo) throws Exception;
}

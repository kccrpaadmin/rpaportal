package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotCostDivideVO;
import com.kcc.biz.model.BotEseroVO;

@Repository("botCostDivideRepository")
public interface BotCostDivideRepository {
	void createBotCostDivideTargetDate(BotCostDivideVO vo) throws Exception;
	List<BotCostDivideVO> listBotCostDivideTargetDate(BotCostDivideVO vo) throws Exception;
}

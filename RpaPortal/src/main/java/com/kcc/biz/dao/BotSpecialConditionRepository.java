package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotSpecialConditionVO;

@Repository("botSpecialConditionRepository")
public interface BotSpecialConditionRepository {
	List<BotSpecialConditionVO> listSpecialCondition(BotSpecialConditionVO vo) throws Exception;
}

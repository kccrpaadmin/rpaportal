package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotSpecialConditionVO;

public interface IBotSpecialConditionService {
	List<BotSpecialConditionVO> listSpecialCondition(BotSpecialConditionVO vo) throws Exception;
}
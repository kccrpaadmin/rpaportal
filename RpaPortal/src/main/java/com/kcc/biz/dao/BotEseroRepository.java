package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotEseroVO;

@Repository("botEseroRepository")
public interface BotEseroRepository {
	List<BotEseroVO> listBotEseroTargetDate(BotEseroVO vo) throws Exception;
}

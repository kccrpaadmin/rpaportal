package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotSystemCheckVO;

@Repository("botSystemCheckRepository")
public interface BotSystemCheckRepository {
	List<BotSystemCheckVO> listBotSystemCheckResult(BotSystemCheckVO vo) throws Exception;
}

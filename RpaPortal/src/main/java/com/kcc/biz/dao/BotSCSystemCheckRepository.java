package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotSCSystemCheckVO;

@Repository("botSCSystemCheckRepository")
public interface BotSCSystemCheckRepository {
	List<BotSCSystemCheckVO> listBotSCSystemCheck(BotSCSystemCheckVO vo) throws Exception;
}

package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotSCSystemCheckVO;

public interface IBotSCSystemCheckService {
	List<BotSCSystemCheckVO> listBotSCSystemCheck(BotSCSystemCheckVO vo) throws Exception;
}
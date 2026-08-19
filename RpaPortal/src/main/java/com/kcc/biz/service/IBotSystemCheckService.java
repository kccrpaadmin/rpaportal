package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotSystemCheckVO;

public interface IBotSystemCheckService {	
	List<BotSystemCheckVO> listBotSystemCheckResult(BotSystemCheckVO vo) throws Exception;
}
package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotPersonalBccVO;

public interface IBotPersonalBccService {
	List<BotPersonalBccVO> listBotPersonalBcc(BotPersonalBccVO vo) throws Exception;	
}
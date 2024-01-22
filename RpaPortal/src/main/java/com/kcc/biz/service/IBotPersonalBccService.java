package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotPersonalBccVO;

public interface IBotPersonalBccService {
	List<BotPersonalBccVO> listPersonalBcc(BotPersonalBccVO vo) throws Exception;	
	List<BotPersonalBccVO> listChgPersonalBcc(BotPersonalBccVO vo) throws Exception;	
}
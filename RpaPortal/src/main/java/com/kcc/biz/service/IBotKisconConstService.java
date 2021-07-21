package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotKisconConstVO;

public interface IBotKisconConstService {	
	List<BotKisconConstVO> listBotKisconConstSubcontract(BotKisconConstVO vo) throws Exception;
	List<BotKisconConstVO> listBotKisconConstManage(BotKisconConstVO vo) throws Exception;
}
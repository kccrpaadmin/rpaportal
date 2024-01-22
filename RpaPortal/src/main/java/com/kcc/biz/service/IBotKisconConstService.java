package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotKisconConstVO;

public interface IBotKisconConstService {	
	List<BotKisconConstVO> listKisconConstSubcontract(BotKisconConstVO vo) throws Exception;
	List<BotKisconConstVO> listKisconConstManage(BotKisconConstVO vo) throws Exception;
	List<BotKisconConstVO> listKisconFirstConstSubcontract(BotKisconConstVO vo) throws Exception;
}
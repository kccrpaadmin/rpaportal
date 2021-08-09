package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotPersonAppointVO;

public interface IBotPersonAppointService {	
	List<BotPersonAppointVO> listBotPersonAppointList(BotPersonAppointVO vo) throws Exception;	
}
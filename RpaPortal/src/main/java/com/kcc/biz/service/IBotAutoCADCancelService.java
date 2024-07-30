package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotAutoCADCancelVO;

public interface IBotAutoCADCancelService {	
	List<BotAutoCADCancelVO> listAutoCADCancelResult(BotAutoCADCancelVO vo) throws Exception;
}
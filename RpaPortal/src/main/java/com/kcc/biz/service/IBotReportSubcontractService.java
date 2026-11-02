package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotReportSubcontractVO;

public interface IBotReportSubcontractService {	
	List<BotReportSubcontractVO> listBotReportSubcontractResult(BotReportSubcontractVO vo) throws Exception;
}
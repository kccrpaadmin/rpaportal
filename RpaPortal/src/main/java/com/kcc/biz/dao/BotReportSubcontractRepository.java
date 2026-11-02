package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotReportSubcontractVO;

@Repository("botReportSubcontractRepository")
public interface BotReportSubcontractRepository {
	List<BotReportSubcontractVO> listBotReportSubcontractResult(BotReportSubcontractVO vo) throws Exception;
}

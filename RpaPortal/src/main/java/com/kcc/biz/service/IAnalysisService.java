package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.AnalysisVO;

public interface IAnalysisService {
	List<AnalysisVO> ListProcessVisits(AnalysisVO vo) throws Exception;
	List<AnalysisVO> ListVisitUser(AnalysisVO vo) throws Exception;
	List<AnalysisVO> ListProcessResult(AnalysisVO vo) throws Exception;
	AnalysisVO getProcessResultTot(AnalysisVO vo) throws Exception;
}
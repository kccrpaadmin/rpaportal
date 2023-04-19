package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.AnalysisVO;

@Repository("analysisRepository")
public interface AnalysisRepository {
	List<AnalysisVO> ListProcessVisits(AnalysisVO vo) throws Exception;
	List<AnalysisVO> ListVisitUser(AnalysisVO vo) throws Exception;
	List<AnalysisVO> ListProcessResult(AnalysisVO vo) throws Exception;
	AnalysisVO getProcessResultTot(AnalysisVO vo) throws Exception;
}

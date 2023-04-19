package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.AnalysisRepository;
import com.kcc.biz.model.AnalysisVO;
import com.kcc.biz.service.IAnalysisService;

@Service("analysisService")
public class AnalysisServiceImpl implements IAnalysisService {
	private static final Logger logger = LoggerFactory.getLogger(AnalysisServiceImpl.class);
	
	@Resource(name="analysisRepository")
	private AnalysisRepository analysisRepository;
	
	public List<AnalysisVO> ListProcessVisits(AnalysisVO vo) throws Exception {
		return analysisRepository.ListProcessVisits(vo);
	}
	public List<AnalysisVO> ListVisitUser(AnalysisVO vo) throws Exception {
		return analysisRepository.ListVisitUser(vo);
	}
	public List<AnalysisVO> ListProcessResult(AnalysisVO vo) throws Exception {
		return analysisRepository.ListProcessResult(vo);
	}
	public AnalysisVO getProcessResultTot(AnalysisVO vo) throws Exception {
		return analysisRepository.getProcessResultTot(vo);
	}
	
}

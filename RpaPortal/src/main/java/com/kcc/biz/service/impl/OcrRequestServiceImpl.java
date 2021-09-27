package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CrawlRequestRepository;
import com.kcc.biz.dao.OcrRequestRepository;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.OcrRequestVO;
import com.kcc.biz.service.ICrawlRequestService;
import com.kcc.biz.service.IOcrRequestService;

@Service("ocrRequestService")
public class OcrRequestServiceImpl implements IOcrRequestService {
	private static final Logger logger = LoggerFactory.getLogger(OcrRequestServiceImpl.class);
	
	@Resource(name="ocrRequestRepository")
	private OcrRequestRepository ocrRequestRepository;
	
	public void createOcrRequest(OcrRequestVO vo) throws Exception {
		ocrRequestRepository.createOcrRequest(vo);
	}
	
	public List<OcrRequestVO> listOcrRequest(OcrRequestVO vo) throws Exception {
	 return ocrRequestRepository.listOcrRequest(vo);
	}
	
	public List<OcrRequestVO> listOcrRequestAttFile(OcrRequestVO vo) throws Exception {
		 return ocrRequestRepository.listOcrRequestAttFile(vo);
	}
	
	public OcrRequestVO getOcrRequestUseRangeOverYn(OcrRequestVO vo) throws Exception {
		 return ocrRequestRepository.getOcrRequestUseRangeOverYn(vo);
	}
}

package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.OcrRequestRepository;
import com.kcc.biz.dao.OcrTextRepository;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.OcrRequestVO;
import com.kcc.biz.model.OcrTextVO;
import com.kcc.biz.service.ICrawlRequestService;
import com.kcc.biz.service.IOcrRequestService;
import com.kcc.biz.service.IOcrTextService;

@Service("ocrTextService")
public class OcrTextServiceImpl implements IOcrTextService {
	private static final Logger logger = LoggerFactory.getLogger(OcrTextServiceImpl.class);
	
	@Resource(name="ocrTextRepository")
	private OcrTextRepository ocrTextRepository;
	
	public void createOcrText(OcrTextVO vo) throws Exception {
		ocrTextRepository.createOcrText(vo);
	}
	
	public List<OcrTextVO> listOcrText(OcrTextVO vo) throws Exception {
		 return ocrTextRepository.listOcrText(vo);
	}
	
	public OcrTextVO getOcrText(OcrTextVO vo) throws Exception {
		 return ocrTextRepository.getOcrText(vo);
	}
}

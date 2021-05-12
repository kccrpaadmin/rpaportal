package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.OcrBusinessLicenseRepository;
import com.kcc.biz.dao.OcrRequestRepository;
import com.kcc.biz.dao.OcrTextRepository;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.model.OcrBusinessLicenseVO;
import com.kcc.biz.model.OcrRequestVO;
import com.kcc.biz.model.OcrTextVO;
import com.kcc.biz.service.ICrawlRequestService;
import com.kcc.biz.service.IOcrBusinessLicenseService;
import com.kcc.biz.service.IOcrRequestService;
import com.kcc.biz.service.IOcrTextService;

@Service("ocrBusinessLicenseService")
public class OcrBusinessLicenseServiceImpl implements IOcrBusinessLicenseService {
	private static final Logger logger = LoggerFactory.getLogger(OcrBusinessLicenseServiceImpl.class);
	
	@Resource(name="ocrBusinessLicenseRepository")
	private OcrBusinessLicenseRepository ocrBusinessLicenseRepository;
	
	public void createOcrBusinessLicense(OcrBusinessLicenseVO vo) throws Exception {
		ocrBusinessLicenseRepository.createOcrBusinessLicense(vo);
	}
	
	public List<OcrBusinessLicenseVO> listOcrBusinessLicense(OcrBusinessLicenseVO vo) throws Exception {
		 return ocrBusinessLicenseRepository.listOcrBusinessLicense(vo);
	}
	
	public OcrBusinessLicenseVO getOcrBusinessLicense(OcrBusinessLicenseVO vo) throws Exception {
		 return ocrBusinessLicenseRepository.getOcrBusinessLicense(vo);
	}
}

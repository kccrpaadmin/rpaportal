package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.OcrRequestVO;

public interface IOcrRequestService {
	void createOcrRequest(OcrRequestVO vo) throws Exception;
	List<OcrRequestVO> listOcrRequest(OcrRequestVO vo) throws Exception;
	List<OcrRequestVO> listOcrRequestAttFile(OcrRequestVO vo) throws Exception;
}

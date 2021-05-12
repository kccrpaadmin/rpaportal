package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.OcrTextVO;

public interface IOcrTextService {
	void createOcrText(OcrTextVO vo) throws Exception;
	List<OcrTextVO> listOcrText(OcrTextVO vo) throws Exception;
	OcrTextVO getOcrText(OcrTextVO vo) throws Exception;
}

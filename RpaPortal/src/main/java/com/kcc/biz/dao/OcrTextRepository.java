package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.kcc.biz.model.OcrTextVO;

@Repository("ocrTextRepository")
public interface OcrTextRepository {
	void createOcrText(OcrTextVO vo) throws Exception;
	List<OcrTextVO> listOcrText(OcrTextVO vo) throws Exception;
	OcrTextVO getOcrText(OcrTextVO vo) throws Exception;
}

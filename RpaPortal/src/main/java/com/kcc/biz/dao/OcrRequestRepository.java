package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.kcc.biz.model.OcrRequestVO;

@Repository("ocrRequestRepository")
public interface OcrRequestRepository {
	void createOcrRequest(OcrRequestVO vo) throws Exception;
	List<OcrRequestVO> listOcrRequest(OcrRequestVO vo) throws Exception;
	List<OcrRequestVO> listOcrRequestAttFile(OcrRequestVO vo) throws Exception;
	OcrRequestVO getOcrRequestUseRangeOverYn(OcrRequestVO vo) throws Exception;
}

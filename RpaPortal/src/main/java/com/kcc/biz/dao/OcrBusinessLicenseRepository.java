package com.kcc.biz.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.kcc.biz.model.OcrBusinessLicenseVO;

@Repository("ocrBusinessLicenseRepository")
public interface OcrBusinessLicenseRepository {
	void createOcrBusinessLicense(OcrBusinessLicenseVO vo) throws Exception;
	List<OcrBusinessLicenseVO> listOcrBusinessLicense(OcrBusinessLicenseVO vo) throws Exception;
	OcrBusinessLicenseVO getOcrBusinessLicense(OcrBusinessLicenseVO vo) throws Exception;
}

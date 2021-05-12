package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.OcrBusinessLicenseVO;

public interface IOcrBusinessLicenseService {
	void createOcrBusinessLicense(OcrBusinessLicenseVO vo) throws Exception;
	List<OcrBusinessLicenseVO> listOcrBusinessLicense(OcrBusinessLicenseVO vo) throws Exception;
	OcrBusinessLicenseVO getOcrBusinessLicense(OcrBusinessLicenseVO vo) throws Exception;
}

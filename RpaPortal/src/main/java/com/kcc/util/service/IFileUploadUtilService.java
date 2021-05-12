package com.kcc.util.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadUtilService {
	String createFiles(List<MultipartFile> files, String menuId, String empNo) throws Exception;
}

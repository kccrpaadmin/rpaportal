package com.kcc.util.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadUtilService {
	String createFiles(List<MultipartFile> files, String menuId, String empNo) throws Exception;
	void saveFiles(String attId, List<String> seqs, List<MultipartFile> files, String menuId, String empNo) throws Exception;
	String createFileControl(String title, String id, String attId, Boolean editable, String pos, String width);
}

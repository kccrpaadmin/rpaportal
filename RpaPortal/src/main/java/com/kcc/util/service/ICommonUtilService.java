package com.kcc.util.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

public interface ICommonUtilService {
	Boolean isEmptyCheck(Object obj);
	String getSHA256(String pwd);
	String getClientIp(HttpServletRequest req);
	String getLocalHostIp();
	String getDeviceType(HttpServletRequest req);
	String getCertInfo();
	String getServerEnv();
	String getFileNm(String filePath);
	String getFileExtension(String filePath);
	byte[] getExtractFileBytes(String filePath);
	byte[] getEncodingBase64Bytes(byte[] targetBytes);
	JSONObject getOcrNaverRequestJsonData(String menuId, String filePath);
	String getDateUserFormat(String formatType, String dateType, int controlDate);
	String getCodeProcedureSelectBox(String selectBoxNm, String procedureNm, String cd, String selectedCd);
}
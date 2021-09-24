package com.kcc.multipart;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.kcc.controller.common.AccountController;

public class IbsheetMultipartResolver extends CommonsMultipartResolver {
	private static final Logger logger = LoggerFactory.getLogger(IbsheetMultipartResolver.class);
	
	public boolean isMultipart(HttpServletRequest request) {
		logger.info("IbsheetMultipartResolver #### " + ServletFileUpload.isMultipartContent(request));

		//ibsheet를 통한 호출, 다운로드나 업로드시 SheetWork 라는 파라미터를 보냄
		String sheetwork = request.getParameter("SheetWork");

		if(sheetwork != null) {
			return false;
		}

		//ibupload를 통한 호출
		String Ajax = request.getHeader("AjaxComponent");

		if("IBUpload".equals(Ajax)) {
			return false;
		}

		return (request != null && ServletFileUpload.isMultipartContent(request));
	}
}

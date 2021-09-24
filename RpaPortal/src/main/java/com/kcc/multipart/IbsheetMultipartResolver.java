package com.kcc.multipart;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class IbsheetMultipartResolver extends CommonsMultipartResolver {

	public boolean isMultipart(HttpServletRequest request) {
		System.out.println("IbsheetMultipartResolver #### " + ServletFileUpload.isMultipartContent(request));

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

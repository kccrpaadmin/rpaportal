package com.kcc.util.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.kcc.biz.model.CrawlRunVO;
import com.kcc.biz.model.StatusMapVO;
import com.kcc.biz.model.StatusVO;

public interface IOcrApiUtilService {
	StatusVO requestOcrVision(String fileFath);
	StatusMapVO requestOcrNaverTemp(String menuId, String filePath);
}
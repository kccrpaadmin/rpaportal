package com.kcc.util.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.kcc.biz.model.CrawlRequestVO;


public interface ICrawlUtilService {
	CrawlRequestVO requestCrawl(CrawlRequestVO vo);
}
package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CrawlRequestVO;

public interface ICrawlRequestService {
	void createCrawlRequest(CrawlRequestVO vo) throws Exception;
	void updateCrawlRequest(CrawlRequestVO vo) throws Exception;
	List<CrawlRequestVO> listCrawlRequest(CrawlRequestVO vo) throws Exception;
	CrawlRequestVO getCrawlRequestStatus(CrawlRequestVO vo) throws Exception;
}
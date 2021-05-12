package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CrawlRequestVO;

@Repository("crawlRequestRepository")
public interface CrawlRequestRepository {
	void createCrawlRequest(CrawlRequestVO vo) throws Exception;
	void updateCrawlRequest(CrawlRequestVO vo) throws Exception;
	List<CrawlRequestVO> listCrawlRequest(CrawlRequestVO vo) throws Exception;
	CrawlRequestVO getCrawlRequestStatus(CrawlRequestVO vo) throws Exception;
}

package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CrawlG2bVO;

public interface ICrawlG2bService {
	List<CrawlG2bVO> listCrawlG2b(CrawlG2bVO vo) throws Exception;
	List<CrawlG2bVO> listCrawlG2bManage(CrawlG2bVO vo) throws Exception;
	void updateCrawlG2bManage(List<CrawlG2bVO> vo) throws Exception;
}
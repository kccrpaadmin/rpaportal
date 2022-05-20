package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CrawlAcdVO;

public interface ICrawlAcdService {
	List<CrawlAcdVO> listCrawlAcd(CrawlAcdVO vo) throws Exception;
	List<CrawlAcdVO> listCrawlAcdManage(CrawlAcdVO vo) throws Exception;
}
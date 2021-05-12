package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CrawlKomisVO;

public interface ICrawlKomisService {
	List<CrawlKomisVO> listCrawlKomis(CrawlKomisVO vo) throws Exception;
	List<CrawlKomisVO> listCrawlKomisManage(CrawlKomisVO vo) throws Exception;
	List<CrawlKomisVO> listCrawlKomisManageAverage(CrawlKomisVO vo) throws Exception;
	CrawlKomisVO getCrawlKomisManageMinOrMaxData(CrawlKomisVO vo) throws Exception;
}
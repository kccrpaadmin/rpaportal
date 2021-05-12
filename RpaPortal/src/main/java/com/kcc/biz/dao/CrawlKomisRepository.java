package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CrawlKomisVO;

@Repository("crawlKomisRepository")
public interface CrawlKomisRepository {
	List<CrawlKomisVO> listCrawlKomis(CrawlKomisVO vo) throws Exception;
	List<CrawlKomisVO> listCrawlKomisManage(CrawlKomisVO vo) throws Exception;
	List<CrawlKomisVO> listCrawlKomisManageAverage(CrawlKomisVO vo) throws Exception;
	CrawlKomisVO getCrawlKomisManageMinOrMaxData(CrawlKomisVO vo) throws Exception;
}

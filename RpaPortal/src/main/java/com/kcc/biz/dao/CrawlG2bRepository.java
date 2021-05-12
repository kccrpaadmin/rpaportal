package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CrawlG2bVO;

@Repository("crawlG2bRepository")
public interface CrawlG2bRepository {
	List<CrawlG2bVO> listCrawlG2b(CrawlG2bVO vo) throws Exception;
	List<CrawlG2bVO> listCrawlG2bManage(CrawlG2bVO vo) throws Exception;
	void updateCrawlG2bManage(CrawlG2bVO vo) throws Exception;
}

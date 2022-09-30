package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CrawlAcdVO;

@Repository("crawlAcdRepository")
public interface CrawlAcdRepository {
	List<CrawlAcdVO> listCrawlAcd(CrawlAcdVO vo) throws Exception;
	List<CrawlAcdVO> listCrawlAcdManage(CrawlAcdVO vo) throws Exception;	
	List<CrawlAcdVO> listAcdManageUseList(CrawlAcdVO vo) throws Exception;
}

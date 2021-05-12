package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CrawlPersonInfoVO;

@Repository("crawlPersonInfoRepository")
public interface CrawlPersonInfoRepository {
	List<CrawlPersonInfoVO> listCrawlPersonInfo(CrawlPersonInfoVO vo) throws Exception;
	List<CrawlPersonInfoVO> listCrawlPersonInfoManage(CrawlPersonInfoVO vo) throws Exception;
}

package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CrawlSystemCheckVO;

@Repository("crawlSystemCheckRepository")
public interface CrawlSystemCheckRepository {
	List<CrawlSystemCheckVO> listCrawlSystemCheckList(CrawlSystemCheckVO vo) throws Exception;
}

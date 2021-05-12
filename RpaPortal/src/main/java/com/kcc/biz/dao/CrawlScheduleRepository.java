package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CrawlScheduleVO;

@Repository("crawlScheduleRepository")
public interface CrawlScheduleRepository {
	void createCrawlSchedule(CrawlScheduleVO vo) throws Exception;
	void deleteCrawlSchedule(CrawlScheduleVO vo) throws Exception;
	List<CrawlScheduleVO> listCrawlSchedule(CrawlScheduleVO vo) throws Exception;
	List<CrawlScheduleVO> listCrawlScheduleMenu(CrawlScheduleVO vo) throws Exception;
}

package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CrawlScheduleVO;

public interface ICrawlScheduleService {
	void createCrawlSchedule(CrawlScheduleVO vo) throws Exception;
	void deleteCrawlSchedule(CrawlScheduleVO vo) throws Exception;
	List<CrawlScheduleVO> listCrawlSchedule(CrawlScheduleVO vo) throws Exception;
	List<CrawlScheduleVO> listCrawlScheduleMenu(CrawlScheduleVO vo) throws Exception;
}

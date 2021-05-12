package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CrawlScheduleRepository;
import com.kcc.biz.model.CrawlScheduleVO;
import com.kcc.biz.service.ICrawlScheduleService;

@Service("crawlScheduleService")
public class CrawlScheduleServiceImpl implements ICrawlScheduleService {
	private static final Logger logger = LoggerFactory.getLogger(CrawlScheduleServiceImpl.class);
	
	@Resource(name="crawlScheduleRepository")
	private CrawlScheduleRepository crawlScheduleRepository;

	public void createCrawlSchedule(CrawlScheduleVO vo) throws Exception {
		crawlScheduleRepository.createCrawlSchedule(vo);
	}
	
	public void deleteCrawlSchedule(CrawlScheduleVO vo) throws Exception {
		crawlScheduleRepository.deleteCrawlSchedule(vo);
	}
	
	public List<CrawlScheduleVO> listCrawlSchedule(CrawlScheduleVO vo) throws Exception {
		return crawlScheduleRepository.listCrawlSchedule(vo);
	}
	
	public List<CrawlScheduleVO> listCrawlScheduleMenu(CrawlScheduleVO vo) throws Exception {
		return crawlScheduleRepository.listCrawlScheduleMenu(vo);
	}
}

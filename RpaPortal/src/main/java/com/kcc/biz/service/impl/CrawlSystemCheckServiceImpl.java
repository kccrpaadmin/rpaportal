package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CrawlSystemCheckRepository;
import com.kcc.biz.model.CrawlSystemCheckVO;
import com.kcc.biz.service.ICrawlSystemCheckService;

@Service("crawlSystemCheckService")
public class CrawlSystemCheckServiceImpl implements ICrawlSystemCheckService {
	private static final Logger logger = LoggerFactory.getLogger(CrawlSystemCheckServiceImpl.class);
	
	@Resource(name="crawlSystemCheckRepository")
	private CrawlSystemCheckRepository crawlSystemCheckRepository;
	
	public List<CrawlSystemCheckVO> listCrawlSystemCheckList(CrawlSystemCheckVO vo) throws Exception {
		return crawlSystemCheckRepository.listCrawlSystemCheckList(vo);
	}	
}

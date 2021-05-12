package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CrawlG2bRepository;
import com.kcc.biz.dao.CrawlSystemCheckRepository;
import com.kcc.biz.model.CrawlG2bVO;
import com.kcc.biz.model.CrawlSystemCheckVO;
import com.kcc.biz.service.ICrawlG2bService;
import com.kcc.biz.service.ICrawlSystemCheckService;

@Service("crawlG2bService")
public class CrawlG2bServiceImpl implements ICrawlG2bService {
	private static final Logger logger = LoggerFactory.getLogger(CrawlG2bServiceImpl.class);
	
	@Resource(name="crawlG2bRepository")
	private CrawlG2bRepository crawlG2bRepository;
	
	public List<CrawlG2bVO> listCrawlG2b(CrawlG2bVO vo) throws Exception {
		return crawlG2bRepository.listCrawlG2b(vo);
	}
	
	public List<CrawlG2bVO> listCrawlG2bManage(CrawlG2bVO vo) throws Exception {
		return crawlG2bRepository.listCrawlG2bManage(vo);
	}
	
	public void updateCrawlG2bManage(List<CrawlG2bVO> vo) throws Exception {
		for (CrawlG2bVO crawlG2bVO : vo) {
			crawlG2bRepository.updateCrawlG2bManage(crawlG2bVO);
		}
	}
}

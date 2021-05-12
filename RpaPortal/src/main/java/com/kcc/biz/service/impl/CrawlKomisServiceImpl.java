package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CrawlG2bRepository;
import com.kcc.biz.dao.CrawlKomisRepository;
import com.kcc.biz.dao.CrawlSystemCheckRepository;
import com.kcc.biz.model.CrawlG2bVO;
import com.kcc.biz.model.CrawlKomisVO;
import com.kcc.biz.model.CrawlSystemCheckVO;
import com.kcc.biz.service.ICrawlG2bService;
import com.kcc.biz.service.ICrawlKomisService;
import com.kcc.biz.service.ICrawlSystemCheckService;

@Service("crawlKomisService")
public class CrawlKomisServiceImpl implements ICrawlKomisService {
	private static final Logger logger = LoggerFactory.getLogger(CrawlKomisServiceImpl.class);
	
	@Resource(name="crawlKomisRepository")
	private CrawlKomisRepository crawlKomisRepository;
	
	public List<CrawlKomisVO> listCrawlKomis(CrawlKomisVO vo) throws Exception {
		return crawlKomisRepository.listCrawlKomis(vo);
	}
	
	public List<CrawlKomisVO> listCrawlKomisManage(CrawlKomisVO vo) throws Exception {
		return crawlKomisRepository.listCrawlKomisManage(vo);
	}
	
	public List<CrawlKomisVO> listCrawlKomisManageAverage(CrawlKomisVO vo) throws Exception {
		return crawlKomisRepository.listCrawlKomisManageAverage(vo);
	}
	
	public CrawlKomisVO getCrawlKomisManageMinOrMaxData(CrawlKomisVO vo) throws Exception {
		return crawlKomisRepository.getCrawlKomisManageMinOrMaxData(vo);
	}
}
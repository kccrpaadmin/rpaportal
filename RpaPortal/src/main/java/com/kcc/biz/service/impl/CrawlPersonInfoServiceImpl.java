package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CrawlG2bRepository;
import com.kcc.biz.dao.CrawlKomisRepository;
import com.kcc.biz.dao.CrawlPersonInfoRepository;
import com.kcc.biz.dao.CrawlSystemCheckRepository;
import com.kcc.biz.model.CrawlG2bVO;
import com.kcc.biz.model.CrawlKomisVO;
import com.kcc.biz.model.CrawlPersonInfoVO;
import com.kcc.biz.model.CrawlSystemCheckVO;
import com.kcc.biz.service.ICrawlG2bService;
import com.kcc.biz.service.ICrawlKomisService;
import com.kcc.biz.service.ICrawlPersonInfoService;
import com.kcc.biz.service.ICrawlSystemCheckService;

@Service("crawlPersonInfoService")
public class CrawlPersonInfoServiceImpl implements ICrawlPersonInfoService {
	private static final Logger logger = LoggerFactory.getLogger(CrawlPersonInfoServiceImpl.class);
	
	@Resource(name="crawlPersonInfoRepository")
	private CrawlPersonInfoRepository crawlPersonInfoRepository;
	
	public List<CrawlPersonInfoVO> listCrawlPersonInfo(CrawlPersonInfoVO vo) throws Exception {
		return crawlPersonInfoRepository.listCrawlPersonInfo(vo);
	}
	
	public List<CrawlPersonInfoVO> listCrawlPersonInfoManage(CrawlPersonInfoVO vo) throws Exception {
		return crawlPersonInfoRepository.listCrawlPersonInfoManage(vo);
	}
}
package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CrawlAcdRepository;
import com.kcc.biz.dao.CrawlSystemCheckRepository;
import com.kcc.biz.model.CrawlAcdVO;
import com.kcc.biz.model.CrawlSystemCheckVO;
import com.kcc.biz.service.ICrawlAcdService;
import com.kcc.biz.service.ICrawlSystemCheckService;

@Service("crawlAcdService")
public class CrawlAcdServiceImpl implements ICrawlAcdService {
	private static final Logger logger = LoggerFactory.getLogger(CrawlAcdServiceImpl.class);
	
	@Resource(name="crawlAcdRepository")
	private CrawlAcdRepository crawlAcdRepository;
	
	public List<CrawlAcdVO> listCrawlAcd(CrawlAcdVO vo) throws Exception {
		return crawlAcdRepository.listCrawlAcd(vo);
	}
	
	public List<CrawlAcdVO> listCrawlAcdManage(CrawlAcdVO vo) throws Exception {
		return crawlAcdRepository.listCrawlAcdManage(vo);
	}
	
	public List<CrawlAcdVO> listAcdManageUseList(CrawlAcdVO vo) throws Exception {
		return crawlAcdRepository.listAcdManageUseList(vo);
	}
}

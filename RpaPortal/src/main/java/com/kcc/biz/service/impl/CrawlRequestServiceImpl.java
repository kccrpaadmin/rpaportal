package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CrawlRequestRepository;
import com.kcc.biz.model.CrawlRequestVO;
import com.kcc.biz.service.ICrawlRequestService;

@Service("crawlRequestService")
public class CrawlRequestServiceImpl implements ICrawlRequestService {
	private static final Logger logger = LoggerFactory.getLogger(CrawlRequestServiceImpl.class);
	
	@Resource(name="crawlRequestRepository")
	private CrawlRequestRepository crawlRequestRepository;

	public void createCrawlRequest(CrawlRequestVO vo) throws Exception {
		crawlRequestRepository.createCrawlRequest(vo);
	}
	
	public void updateCrawlRequest(CrawlRequestVO vo) throws Exception {
		crawlRequestRepository.updateCrawlRequest(vo);
	}
	
	public List<CrawlRequestVO> listCrawlRequest(CrawlRequestVO vo) throws Exception {
		return crawlRequestRepository.listCrawlRequest(vo);
	}
	
	public CrawlRequestVO getCrawlRequestStatus(CrawlRequestVO vo) throws Exception {
		return crawlRequestRepository.getCrawlRequestStatus(vo);
	}
}

package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.CrawlG2bRepository;
import com.kcc.biz.dao.CrawlKomisRepository;
import com.kcc.biz.dao.CrawlMaterialRepository;
import com.kcc.biz.dao.CrawlSystemCheckRepository;
import com.kcc.biz.model.CrawlG2bVO;
import com.kcc.biz.model.CrawlKomisVO;
import com.kcc.biz.model.CrawlMaterialVO;
import com.kcc.biz.model.CrawlSystemCheckVO;
import com.kcc.biz.service.ICrawlG2bService;
import com.kcc.biz.service.ICrawlKomisService;
import com.kcc.biz.service.ICrawlMaterialService;
import com.kcc.biz.service.ICrawlSystemCheckService;

@Service("crawlMaterialService")
public class CrawlMaterialServiceImpl implements ICrawlMaterialService {
	private static final Logger logger = LoggerFactory.getLogger(CrawlMaterialServiceImpl.class);
	
	@Resource(name="crawlMaterialRepository")
	private CrawlMaterialRepository crawlMaterialRepository;
	
	public List<CrawlMaterialVO> listCrawlMaterial(CrawlMaterialVO vo) throws Exception {
		return crawlMaterialRepository.listCrawlMaterial(vo);
	}
	
	public List<CrawlMaterialVO> listCrawlMaterialManageSteelScrap(CrawlMaterialVO vo) throws Exception {
		return crawlMaterialRepository.listCrawlMaterialManageSteelScrap(vo);
	}
	
	public List<CrawlMaterialVO> listCrawlMaterialManageSteelScrapAndRebar(CrawlMaterialVO vo) throws Exception {
		return crawlMaterialRepository.listCrawlMaterialManageSteelScrapAndRebar(vo);
	}
	
	public List<CrawlMaterialVO> listCrawlMaterialManageRebarPlan(CrawlMaterialVO vo) throws Exception {
		return crawlMaterialRepository.listCrawlMaterialManageRebarPlan(vo);
	}
}
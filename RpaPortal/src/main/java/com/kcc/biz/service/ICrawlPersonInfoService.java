package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CrawlKomisVO;
import com.kcc.biz.model.CrawlPersonInfoVO;

public interface ICrawlPersonInfoService {
	List<CrawlPersonInfoVO> listCrawlPersonInfo(CrawlPersonInfoVO vo) throws Exception;
	List<CrawlPersonInfoVO> listCrawlPersonInfoManage(CrawlPersonInfoVO vo) throws Exception;
}
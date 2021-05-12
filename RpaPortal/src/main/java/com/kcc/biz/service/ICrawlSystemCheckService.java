package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CrawlSystemCheckVO;

public interface ICrawlSystemCheckService {
	List<CrawlSystemCheckVO> listCrawlSystemCheckList(CrawlSystemCheckVO vo) throws Exception;
}
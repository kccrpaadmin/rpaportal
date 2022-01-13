package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.CrawlKomisVO;
import com.kcc.biz.model.CrawlMaterialVO;

public interface ICrawlMaterialService {
	List<CrawlMaterialVO> listCrawlMaterial(CrawlMaterialVO vo) throws Exception;
}
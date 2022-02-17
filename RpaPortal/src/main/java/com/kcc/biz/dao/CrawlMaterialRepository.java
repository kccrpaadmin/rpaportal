package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CrawlKomisVO;
import com.kcc.biz.model.CrawlMaterialVO;

@Repository("crawlMaterialRepository")
public interface CrawlMaterialRepository {
	List<CrawlMaterialVO> listCrawlMaterial(CrawlMaterialVO vo) throws Exception;
	List<CrawlMaterialVO> listCrawlMaterialManageSteelScrap(CrawlMaterialVO vo) throws Exception;
	List<CrawlMaterialVO> listCrawlMaterialManageSteelScrapAndRebar(CrawlMaterialVO vo) throws Exception;
}

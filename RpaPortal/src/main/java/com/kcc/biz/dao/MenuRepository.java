package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.MenuVO;

@Repository("menuRepository")
public interface MenuRepository {
	List<MenuVO> listCrawlMenu(MenuVO vo) throws Exception;
	MenuVO getCrawlMenu(MenuVO vo) throws Exception;
	List<MenuVO> listOcrMenu(MenuVO vo) throws Exception;
	MenuVO getOcrMenu(MenuVO vo) throws Exception;
	List<MenuVO> listBotMenu(MenuVO vo) throws Exception;
	MenuVO getBotMenu(MenuVO vo) throws Exception;
	MenuVO getMenuAuth(MenuVO vo) throws Exception;
}

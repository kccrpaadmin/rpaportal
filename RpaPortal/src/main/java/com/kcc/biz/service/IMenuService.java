package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.MenuVO;

public interface IMenuService {
	List<MenuVO> listCrawlMenu(MenuVO vo) throws Exception;
	MenuVO getCrawlMenu(MenuVO vo) throws Exception;
	List<MenuVO> listOcrMenu(MenuVO vo) throws Exception;
	MenuVO getOcrMenu(MenuVO vo) throws Exception;
	List<MenuVO> listBotMenu(MenuVO vo) throws Exception;
	MenuVO getBotMenu(MenuVO vo) throws Exception;
	MenuVO getMenuAuth(MenuVO vo) throws Exception;
}
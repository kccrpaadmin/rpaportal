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
	List<MenuVO> listMenuTree(MenuVO vo) throws Exception;
	List<MenuVO> listMenuChild(MenuVO vo) throws Exception;
	void saveMenuManage(List<MenuVO> vo) throws Exception;
	List<MenuVO> listMenuAuthRunChild(MenuVO vo) throws Exception;
	List<MenuVO> listMenuAuthManageChild(MenuVO vo) throws Exception;
	List<MenuVO> listAuthManageSearchUser(MenuVO vo) throws Exception;
	List<MenuVO> listAuthManageSearchDegree(MenuVO vo) throws Exception;
	List<MenuVO> listAuthManageSearchDept(MenuVO vo) throws Exception;
	void createAuthManage(MenuVO vo) throws Exception;
	void deleteAuthManage(List<MenuVO> vo) throws Exception;	
}
package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEngineerVO;
import com.kcc.biz.model.CrawlG2bVO;

public interface IBotEngineerService {	
	List<BotEngineerVO> listBotEngineerBasic(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerQualityRank(BotEngineerVO vo) throws Exception;		
	List<BotEngineerVO> listBotEngineerQualityEduPass(BotEngineerVO vo) throws Exception;			
	List<BotEngineerVO> listBotEngineerQualityEduFail(BotEngineerVO vo) throws Exception;			
	List<BotEngineerVO> listBotEngineerCareerList(BotEngineerVO vo) throws Exception;		
	List<BotEngineerVO> listBotEngineerManage(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerManageCareerList(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerTargetUserList(BotEngineerVO vo) throws Exception;
	void deleteBotEngineerTargetUser(List<BotEngineerVO> vo) throws Exception;	
	void createBotEngineerTargetUser(BotEngineerVO vo) throws Exception;
	List<BotEngineerVO> listBotEngineerTargetUserSearchUser(BotEngineerVO vo) throws Exception;
}
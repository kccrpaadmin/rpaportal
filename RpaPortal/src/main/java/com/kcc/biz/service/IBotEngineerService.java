package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEngineerVO;

public interface IBotEngineerService {	
	List<BotEngineerVO> listBotEngineerBasic(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerQualityRank(BotEngineerVO vo) throws Exception;		
	List<BotEngineerVO> listBotEngineerQualityEduPass(BotEngineerVO vo) throws Exception;			
	List<BotEngineerVO> listBotEngineerQualityEduFail(BotEngineerVO vo) throws Exception;			
	List<BotEngineerVO> listBotEngineerCareerList(BotEngineerVO vo) throws Exception;		
	List<BotEngineerVO> listBotEngineerManage(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerManageCareerList(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerTargetUser(BotEngineerVO vo) throws Exception;
	void saveBotEngineerTargetUser(BotEngineerVO vo) throws Exception;
	List<BotEngineerVO> listBotEngineerTargetUserSearchUser(BotEngineerVO vo) throws Exception;
}
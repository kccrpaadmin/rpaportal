package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotEngineerVO;

@Repository("botEngineerRepository")
public interface BotEngineerRepository {	
	List<BotEngineerVO> listBotEngineerBasic(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerQualityRank(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerQualityEduPass(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerQualityEduFail(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerCareerList(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerManage(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerManageCareerList(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerTargetUserList(BotEngineerVO vo) throws Exception;	
	void saveBotEngineerTargetUser(BotEngineerVO vo) throws Exception;	
	List<BotEngineerVO> listBotEngineerTargetUserSearchUser(BotEngineerVO vo) throws Exception;	
}

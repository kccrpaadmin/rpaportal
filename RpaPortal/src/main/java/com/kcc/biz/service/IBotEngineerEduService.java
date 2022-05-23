package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEngineerEduVO;

public interface IBotEngineerEduService {	
	List<BotEngineerEduVO> listBotEngineerConstEduPass(BotEngineerEduVO vo) throws Exception;	
	List<BotEngineerEduVO> listBotEngineerConstEduFail(BotEngineerEduVO vo) throws Exception;
	List<BotEngineerEduVO> listBotEngineerEduManageQualityEdu(BotEngineerEduVO vo) throws Exception;
	List<BotEngineerEduVO> listBotEngineerEduManageConstEdu(BotEngineerEduVO vo) throws Exception;
}
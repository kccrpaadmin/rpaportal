package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotEngineerEduVO;
import com.kcc.biz.model.BotEngineerVO;

@Repository("botEngineerEduRepository")
public interface BotEngineerEduRepository {	
	List<BotEngineerEduVO> listBotEngineerConstEduPass(BotEngineerEduVO vo) throws Exception;	
	List<BotEngineerEduVO> listBotEngineerConstEduFail(BotEngineerEduVO vo) throws Exception;
	List<BotEngineerEduVO> listBotEngineerEduManageQualityEdu(BotEngineerEduVO vo) throws Exception;
	List<BotEngineerEduVO> listBotEngineerEduManageConstEdu(BotEngineerEduVO vo) throws Exception;
}

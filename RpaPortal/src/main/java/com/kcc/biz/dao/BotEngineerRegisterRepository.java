package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotEngineerRegisterVO;
import com.kcc.biz.model.BotStampTaxSlipDataVO;

@Repository("botEngineerRegisterRepository")
public interface BotEngineerRegisterRepository {
	List<BotEngineerRegisterVO> listBotEngineerRegisterResult(BotEngineerRegisterVO vo) throws Exception;
}

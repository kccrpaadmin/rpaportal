package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotEtcTaxVO;

@Repository("botEtcTaxRepository")
public interface BotEtcTaxRepository {	
	List<BotEtcTaxVO> listBotEtcTaxList(BotEtcTaxVO vo) throws Exception;
	BotEtcTaxVO getBotEtcTaxAttId(BotEtcTaxVO vo) throws Exception;
}

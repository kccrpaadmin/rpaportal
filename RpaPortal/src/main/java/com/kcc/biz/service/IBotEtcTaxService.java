package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEtcTaxVO;

public interface IBotEtcTaxService {	
	List<BotEtcTaxVO> listBotEtcTaxList(BotEtcTaxVO vo) throws Exception;
}
package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotContractElecStampTaxVO;

public interface IBotContractElecStampTaxService {	
	List<BotContractElecStampTaxVO> listContractElecStampTaxResult(BotContractElecStampTaxVO vo) throws Exception;
}
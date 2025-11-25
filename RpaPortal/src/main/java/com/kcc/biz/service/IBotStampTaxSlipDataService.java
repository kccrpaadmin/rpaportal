package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotStampTaxSlipDataVO;

public interface IBotStampTaxSlipDataService {	
	List<BotStampTaxSlipDataVO> listStampTaxSlipDataResult(BotStampTaxSlipDataVO vo) throws Exception;
}
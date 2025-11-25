package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotStampTaxSlipDataVO;

@Repository("botStampTaxSlipDataRepository")
public interface BotStampTaxSlipDataRepository {
	List<BotStampTaxSlipDataVO> listStampTaxSlipDataResult(BotStampTaxSlipDataVO vo) throws Exception;
}

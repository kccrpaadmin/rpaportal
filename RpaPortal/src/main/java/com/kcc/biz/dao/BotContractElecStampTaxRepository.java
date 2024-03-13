package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotContractElecStampTaxVO;

@Repository("botContractElecStampTaxRepository")
public interface BotContractElecStampTaxRepository {
	List<BotContractElecStampTaxVO> listContractElecStampTaxResult(BotContractElecStampTaxVO vo) throws Exception;
}

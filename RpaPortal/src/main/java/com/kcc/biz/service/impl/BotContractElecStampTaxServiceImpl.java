package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotContractElecStampTaxRepository;
import com.kcc.biz.model.BotContractElecStampTaxVO;
import com.kcc.biz.service.IBotContractElecStampTaxService;

@Service("botContractElecStampTaxService")
public class BotContractElecStampTaxServiceImpl implements IBotContractElecStampTaxService {
	private static final Logger logger = LoggerFactory.getLogger(BotContractElecStampTaxServiceImpl.class);
	
	@Resource(name="botContractElecStampTaxRepository")
	private BotContractElecStampTaxRepository botContractElecStampTaxRepository;
	
	public List<BotContractElecStampTaxVO> listContractElecStampTaxResult(BotContractElecStampTaxVO vo) throws Exception {
		return botContractElecStampTaxRepository.listContractElecStampTaxResult(vo);
	}
}

package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotStampTaxSlipDataRepository;
import com.kcc.biz.model.BotStampTaxSlipDataVO;
import com.kcc.biz.service.IBotStampTaxSlipDataService;

@Service("botStampTaxSlipDataService")
public class BotStampTaxSlipDataServiceImpl implements IBotStampTaxSlipDataService {
	private static final Logger logger = LoggerFactory.getLogger(BotStampTaxSlipDataServiceImpl.class);
	
	@Resource(name="botStampTaxSlipDataRepository")
	private BotStampTaxSlipDataRepository botStampTaxSlipDataRepository;
	
	public List<BotStampTaxSlipDataVO> listStampTaxSlipDataResult(BotStampTaxSlipDataVO vo) throws Exception {
		return botStampTaxSlipDataRepository.listStampTaxSlipDataResult(vo);
	}
}

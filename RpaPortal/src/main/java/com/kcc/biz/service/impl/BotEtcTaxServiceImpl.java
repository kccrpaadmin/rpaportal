package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEtcTaxRepository;
import com.kcc.biz.model.BotEtcTaxVO;
import com.kcc.biz.service.IBotEtcTaxService;

@Service("botEtcTaxService")
public class BotEtcTaxServiceImpl implements IBotEtcTaxService {
	private static final Logger logger = LoggerFactory.getLogger(BotEtcTaxServiceImpl.class);
	
	@Resource(name="botEtcTaxRepository")
	private BotEtcTaxRepository botEtcTaxRepository;
		
	public List<BotEtcTaxVO> listBotEtcTaxList(BotEtcTaxVO vo) throws Exception {
		return botEtcTaxRepository.listBotEtcTaxList(vo);
	}
}

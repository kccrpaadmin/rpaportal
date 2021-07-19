package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotEseroRepository;
import com.kcc.biz.model.BotEseroVO;
import com.kcc.biz.service.IBotEseroService;

@Service("botEseroService")
public class BotEseroServiceImpl implements IBotEseroService {
	private static final Logger logger = LoggerFactory.getLogger(BotEseroServiceImpl.class);
	
	@Resource(name="botEseroRepository")
	private BotEseroRepository botEseroRepository;
	
	public void saveBotEseroTargetDate(BotEseroVO vo) throws Exception {
		botEseroRepository.saveBotEseroTargetDate(vo);
	}
	
	public List<BotEseroVO> listBotEseroTargetDate(BotEseroVO vo) throws Exception {
		return botEseroRepository.listBotEseroTargetDate(vo);
	}
	
	public List<BotEseroVO> listBotEseroInvoiceList(BotEseroVO vo) throws Exception {
		return botEseroRepository.listBotEseroInvoiceList(vo);
	}
	
	public List<BotEseroVO> listBotEseroSlipList(BotEseroVO vo) throws Exception {
		return botEseroRepository.listBotEseroSlipList(vo);
	}
	
	public List<BotEseroVO> listBotEseroInvoiceSlipListManageTaxOn(BotEseroVO vo) throws Exception {
		return botEseroRepository.listBotEseroInvoiceSlipListManageTaxOn(vo);
	}
	
	public List<BotEseroVO> listBotEseroInvoiceSlipListManageTaxOff(BotEseroVO vo) throws Exception {
		return botEseroRepository.listBotEseroInvoiceSlipListManageTaxOff(vo);
	}
}

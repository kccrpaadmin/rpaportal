package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotSalesEseroRepository;
import com.kcc.biz.model.BotSalesEseroVO;
import com.kcc.biz.service.IBotSalesEseroService;

@Service("botSalesEseroService")
public class BotSalesEseroServiceImpl implements IBotSalesEseroService {
	private static final Logger logger = LoggerFactory.getLogger(BotSalesEseroServiceImpl.class);
	
	@Resource(name="botSalesEseroRepository")
	private BotSalesEseroRepository botSalesEseroRepository;
	
	public void saveBotSalesEseroTargetDate(BotSalesEseroVO vo) throws Exception {
		botSalesEseroRepository.saveBotSalesEseroTargetDate(vo);
	}
	
	public List<BotSalesEseroVO> listBotSalesEseroTargetDate(BotSalesEseroVO vo) throws Exception {
		return botSalesEseroRepository.listBotSalesEseroTargetDate(vo);
	}
	
	public List<BotSalesEseroVO> listBotICHomeTaxList(BotSalesEseroVO vo) throws Exception {
		return botSalesEseroRepository.listBotICHomeTaxList(vo);
	}
	
	public List<BotSalesEseroVO> listBotICOracleList(BotSalesEseroVO vo) throws Exception {
		return botSalesEseroRepository.listBotICOracleList(vo);
	}
	
	public List<BotSalesEseroVO> listBotICSalesSlipList(BotSalesEseroVO vo) throws Exception {
		return botSalesEseroRepository.listBotICSalesSlipList(vo);
	}
	
	public List<BotSalesEseroVO> listBotICBuySlipList(BotSalesEseroVO vo) throws Exception {
		return botSalesEseroRepository.listBotICBuySlipList(vo);
	}
	
	public List<BotSalesEseroVO> listBotICTotalList(BotSalesEseroVO vo) throws Exception {
		return botSalesEseroRepository.listBotICTotalList(vo);
	}
	
	public List<BotSalesEseroVO> listSalesEseroDetail(BotSalesEseroVO vo) throws Exception {
		return botSalesEseroRepository.listSalesEseroDetail(vo);
	}
	
	public List<BotSalesEseroVO> listBotSalesEseroManageVendorInvoiceList(BotSalesEseroVO vo) throws Exception {
		return botSalesEseroRepository.listBotSalesEseroManageVendorInvoiceList(vo);
	}
	
}

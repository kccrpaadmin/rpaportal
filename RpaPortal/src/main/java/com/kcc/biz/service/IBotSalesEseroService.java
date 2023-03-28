package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotSalesEseroVO;

public interface IBotSalesEseroService {
	void saveBotSalesEseroTargetDate(BotSalesEseroVO vo) throws Exception;
	List<BotSalesEseroVO> listBotSalesEseroTargetDate(BotSalesEseroVO vo) throws Exception;
	List<BotSalesEseroVO> listBotICHomeTaxList(BotSalesEseroVO vo) throws Exception;
	List<BotSalesEseroVO> listBotICOracleList(BotSalesEseroVO vo) throws Exception;
	List<BotSalesEseroVO> listBotICSalesSlipList(BotSalesEseroVO vo) throws Exception;
	List<BotSalesEseroVO> listBotICBuySlipList(BotSalesEseroVO vo) throws Exception;
	List<BotSalesEseroVO> listBotICTotalList(BotSalesEseroVO vo) throws Exception;
	List<BotSalesEseroVO> listSalesEseroDetail(BotSalesEseroVO vo) throws Exception;
	List<BotSalesEseroVO> listBotSalesEseroManageVendorInvoiceList(BotSalesEseroVO vo) throws Exception;
	
}
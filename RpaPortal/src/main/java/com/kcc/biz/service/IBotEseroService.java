package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotEseroVO;
import com.kcc.biz.model.MenuVO;

public interface IBotEseroService {
	void saveBotEseroTargetDate(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroTargetDate(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroInvoiceList(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroSlipList(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroInvoiceSlipListManageTaxOn(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroInvoiceSlipListManageTaxOff(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroInvoiceSlipListManageOraCheckList(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroManageVendorInvoiceList(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroManageVendorSlipList(BotEseroVO vo) throws Exception;
	BotEseroVO getBotEseroManageVendorInfo(BotEseroVO vo) throws Exception;
}
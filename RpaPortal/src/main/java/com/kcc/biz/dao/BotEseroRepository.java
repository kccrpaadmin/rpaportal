package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotEseroVO;

@Repository("botEseroRepository")
public interface BotEseroRepository {
	void saveBotEseroTargetDate(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroTargetDate(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroInvoiceList(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroSlipList(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroInvoiceSlipListManageTaxOn(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroInvoiceSlipListManageTaxOff(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroInvoiceSlipListManageOraCheckList(BotEseroVO vo) throws Exception;
	List<BotEseroVO> listBotEseroManageVendorSlipList(BotEseroVO vo) throws Exception;
	BotEseroVO getBotEseroManageVendorInfo(BotEseroVO vo) throws Exception;
}

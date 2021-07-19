package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotMoneySendVO;

@Repository("botMoneySendRepository")
public interface BotMoneySendRepository {	
	List<BotMoneySendVO> listBotMoneySendList(BotMoneySendVO vo) throws Exception;
	List<BotMoneySendVO> listBotMoneySendManageSearchVendor(BotMoneySendVO vo) throws Exception;;
	List<BotMoneySendVO> listBotMoneySendManage(BotMoneySendVO vo) throws Exception;;
	List<BotMoneySendVO> listBotMoneySendManageSendAmt(BotMoneySendVO vo) throws Exception;;
}

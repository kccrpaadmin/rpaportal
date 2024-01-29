package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotDelayedPaymentVO;

public interface IBotDelayedPaymentService {	
	List<BotDelayedPaymentVO> ListDelayedPaymentResult(BotDelayedPaymentVO vo) throws Exception;
	List<BotDelayedPaymentVO> listBotDelayedPaymentManage(BotDelayedPaymentVO vo) throws Exception;
}
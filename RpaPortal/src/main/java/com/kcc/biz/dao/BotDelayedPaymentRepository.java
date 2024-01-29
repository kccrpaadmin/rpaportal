package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotDelayedPaymentVO;

@Repository("botDelayedPaymentRepository")
public interface BotDelayedPaymentRepository {	
	List<BotDelayedPaymentVO> ListDelayedPaymentResult(BotDelayedPaymentVO vo) throws Exception;
	List<BotDelayedPaymentVO> listBotDelayedPaymentManage(BotDelayedPaymentVO vo) throws Exception;
}

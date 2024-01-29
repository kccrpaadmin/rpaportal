package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotDelayedPaymentRepository;
import com.kcc.biz.model.BotDelayedPaymentVO;
import com.kcc.biz.service.IBotDelayedPaymentService;

@Service("botDelayedPaymentService")
public class BotDelayedPaymentServiceImpl implements IBotDelayedPaymentService {
	private static final Logger logger = LoggerFactory.getLogger(BotDelayedPaymentServiceImpl.class);
	
	@Resource(name="botDelayedPaymentRepository")
	private BotDelayedPaymentRepository botDelayedPaymentRepository;
		
	public List<BotDelayedPaymentVO> ListDelayedPaymentResult(BotDelayedPaymentVO vo) throws Exception {
		return botDelayedPaymentRepository.ListDelayedPaymentResult(vo);
	}
	
	public List<BotDelayedPaymentVO> listBotDelayedPaymentManage(BotDelayedPaymentVO vo) throws Exception {
		return botDelayedPaymentRepository.listBotDelayedPaymentManage(vo);
	}
}

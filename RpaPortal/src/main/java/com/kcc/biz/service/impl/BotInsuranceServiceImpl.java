package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotInsuranceRepository;
import com.kcc.biz.model.BotInsuranceVO;
import com.kcc.biz.service.IBotInsuranceService;

@Service("botInsuranceService")
public class BotInsuranceServiceImpl implements IBotInsuranceService {
	private static final Logger logger = LoggerFactory.getLogger(BotInsuranceServiceImpl.class);
	
	@Resource(name="botInsuranceRepository")
	private BotInsuranceRepository botInsuranceRepository;
	
	public List<BotInsuranceVO> listInsurance(BotInsuranceVO vo) throws Exception {
		return botInsuranceRepository.listInsurance(vo);
	}
	
}

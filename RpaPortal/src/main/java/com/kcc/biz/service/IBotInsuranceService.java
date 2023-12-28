package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotInsuranceVO;

public interface IBotInsuranceService {
	List<BotInsuranceVO> listInsurance(BotInsuranceVO vo) throws Exception;
}
package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotInsuranceVO;

@Repository("botInsuranceRepository")
public interface BotInsuranceRepository {
	List<BotInsuranceVO> listInsurance(BotInsuranceVO vo) throws Exception;
}

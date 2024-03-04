package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotCompanyRestrictionVO;

public interface IBotCompanyRestrictionService {	
	List<BotCompanyRestrictionVO> listCompanyRestrictionResult(BotCompanyRestrictionVO vo) throws Exception;
}
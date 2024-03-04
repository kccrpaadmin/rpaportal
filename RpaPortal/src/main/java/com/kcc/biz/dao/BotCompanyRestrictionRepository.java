package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotCompanyRestrictionVO;

@Repository("botCompanyRestrictionRepository")
public interface BotCompanyRestrictionRepository {
	List<BotCompanyRestrictionVO> listCompanyRestrictionResult(BotCompanyRestrictionVO vo) throws Exception;
}

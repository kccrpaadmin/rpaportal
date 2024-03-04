package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotCompanyRestrictionRepository;
import com.kcc.biz.model.BotCompanyRestrictionVO;
import com.kcc.biz.service.IBotCompanyRestrictionService;

@Service("botCompanyRestrictionService")
public class BotCompanyRestrictionServiceImpl implements IBotCompanyRestrictionService {
	private static final Logger logger = LoggerFactory.getLogger(BotCompanyRestrictionServiceImpl.class);
	
	@Resource(name="botCompanyRestrictionRepository")
	private BotCompanyRestrictionRepository botCompanyRestrictionRepository;
	
	public List<BotCompanyRestrictionVO> listCompanyRestrictionResult(BotCompanyRestrictionVO vo) throws Exception {
		return botCompanyRestrictionRepository.listCompanyRestrictionResult(vo);
	}
}

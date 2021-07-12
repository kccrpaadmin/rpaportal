package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BotVendorRepository;
import com.kcc.biz.model.BotVendorVO;
import com.kcc.biz.service.IBotVendorService;

@Service("botVendorService")
public class BotVendorServiceImpl implements IBotVendorService {
	private static final Logger logger = LoggerFactory.getLogger(BotVendorServiceImpl.class);
	
	@Resource(name="botVendorRepository")
	private BotVendorRepository botVendorRepository;
		
	public List<BotVendorVO> listBotVendor(BotVendorVO vo) throws Exception {
		return botVendorRepository.listBotVendor(vo);
	}	
}

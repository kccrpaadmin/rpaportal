package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BotVendorVO;

public interface IBotVendorService {	
	List<BotVendorVO> listBotVendor(BotVendorVO vo) throws Exception;
}
package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BotVendorVO;

@Repository("botVendorRepository")
public interface BotVendorRepository {	
	List<BotVendorVO> listBotVendor(BotVendorVO vo) throws Exception;;
}

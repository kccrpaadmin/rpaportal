package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.KakaoRepository;
import com.kcc.biz.model.KakaoVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.service.IKakaoService;

@Service("kakaoService")
public class KakaoServiceImpl implements IKakaoService {
	private static final Logger logger = LoggerFactory.getLogger(KakaoServiceImpl.class);
	
	@Resource(name="kakaoRepository")
	private KakaoRepository kakaoRepository;
	
	public List<KakaoVO> listKakaoTemplate(KakaoVO vo) throws Exception {
		return kakaoRepository.listKakaoTemplate(vo);
	}
	
	public void saveKakaoManage(List<KakaoVO> vo) throws Exception {
		for (KakaoVO kakaoVO : vo) {
			kakaoRepository.saveKakaoManage(kakaoVO);
		}
	}
}

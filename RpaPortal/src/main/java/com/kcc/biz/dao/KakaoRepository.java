package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.KakaoVO;

@Repository("kakaoRepository")
public interface KakaoRepository {
	List<KakaoVO> listKakaoTemplate(KakaoVO vo) throws Exception;
	void saveKakaoManage(KakaoVO vo) throws Exception;
}

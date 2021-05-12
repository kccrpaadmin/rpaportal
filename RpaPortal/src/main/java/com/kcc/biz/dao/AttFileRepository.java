package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.kcc.biz.model.AttFileVO;

@Repository("attFileRepository")
public interface AttFileRepository {
	void createAtt(AttFileVO vo) throws Exception;
	void createAttFile(AttFileVO vo) throws Exception;
	List<AttFileVO> listAttFile(AttFileVO vo) throws Exception;
}

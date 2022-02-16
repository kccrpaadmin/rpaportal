package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CodeVO;

@Repository("codeRepository")
public interface CodeRepository {
	List<CodeVO> listCodeProcedure(CodeVO vo) throws Exception;
	List<CodeVO> listCodeTree(CodeVO vo) throws Exception;
	List<CodeVO> listCodeChild(CodeVO vo) throws Exception;
	void saveCodeManage(CodeVO vo) throws Exception;	
	CodeVO getGridCodeCombo(CodeVO vo) throws Exception;
}

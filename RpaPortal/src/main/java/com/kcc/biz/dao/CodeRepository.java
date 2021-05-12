package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.CodeVO;

@Repository("codeRepository")
public interface CodeRepository {
	List<CodeVO> listCodeProcedure(CodeVO vo) throws Exception;
}

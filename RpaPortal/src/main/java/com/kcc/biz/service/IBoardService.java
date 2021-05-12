package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BoardVO;

public interface IBoardService {
	List<BoardVO> listBasicBoard(BoardVO vo) throws Exception;
}
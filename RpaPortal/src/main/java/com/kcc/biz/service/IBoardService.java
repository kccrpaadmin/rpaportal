package com.kcc.biz.service;

import java.util.List;

import com.kcc.biz.model.BoardVO;

public interface IBoardService {
	List<BoardVO> listBasicBoard(BoardVO vo) throws Exception;
	BoardVO getBoardDetail(BoardVO vo) throws Exception;
	void deleteBoard(BoardVO vo) throws Exception;
	void createBoardWrite(BoardVO vo) throws Exception;
	void updateBoardWrite(BoardVO vo) throws Exception;
}
package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BoardVO;

@Repository("boardRepository")
public interface BoardRepository {
	List<BoardVO> listBasicBoard(BoardVO vo) throws Exception;
	BoardVO getBoardDetail(BoardVO vo) throws Exception;
	void deleteBoard(BoardVO vo) throws Exception;
	void createBoardWrite(BoardVO vo) throws Exception;
	void updateBoardWrite(BoardVO vo) throws Exception;
}

package com.kcc.biz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kcc.biz.model.BoardVO;

@Repository("boardRepository")
public interface BoardRepository {
	List<BoardVO> listBasicBoard(BoardVO vo) throws Exception;
}

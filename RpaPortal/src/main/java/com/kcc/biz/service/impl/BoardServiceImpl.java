package com.kcc.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kcc.biz.dao.BoardRepository;
import com.kcc.biz.dao.MenuRepository;
import com.kcc.biz.dao.UserRepository;
import com.kcc.biz.model.BoardVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IBoardService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;

@Service("boardService")
public class BoardServiceImpl implements IBoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Resource(name="boardRepository")
	private BoardRepository boardRepository;
	
	public List<BoardVO> listBasicBoard(BoardVO vo) throws Exception {
		return boardRepository.listBasicBoard(vo);
	}
}

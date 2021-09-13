package com.kcc.controller.menu;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.BoardVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IBoardService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.IFileUploadUtilService;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/Board")
@Controller
public class BoardController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@Resource(name="fileUploadUtilService")
	private IFileUploadUtilService fileUploadUtilService;
		
	@GetMapping("/ListBoard.do")
	public String ListBoard() {
		logger.info("/Board/ListBoard.do");
		return "Board/ListBoard";
	}
	
	@GetMapping("BoardDetail.do")
	public String BoardDetail(String pSeq, String pMode, Model model) {
		logger.info("/Board/BoardDetail.do");
		
		// BoardVO 입력
		BoardVO inBoardVO = new BoardVO();
		inBoardVO.setSeq(pSeq);
		inBoardVO.setMode(pMode);
		
		// BoardVO 출력
		BoardVO outBoardVO = new BoardVO();
		
		try {
			// 공지사항 상세 조회
			outBoardVO = boardService.getBoardDetail(inBoardVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// 모델 정의
		model.addAttribute("outBoardVO", outBoardVO);
		model.addAttribute("attIdFileBox", fileUploadUtilService.createFileControl("첨부파일", "attId", commonUtilService.isEmptyCheck(outBoardVO) ? "" :  outBoardVO.getAttId(), false, "Left", "49%"));
		
		return "Board/BoardDetail";
	}
	
	// 공지사항 작성 화면 조회
	@GetMapping("BoardWrite.do")
	public String BoardWrite(String pSeq, String pMode, String pSaveMode, Model model) {
		logger.info("/Board/BoardWrite.do");

		// BoardVO 입력
		BoardVO inBoardVO = new BoardVO();
		inBoardVO.setSeq(pSeq);
		inBoardVO.setMode(pMode);
		
		// BoardVO 출력
		BoardVO outBoardVO = new BoardVO();
		
		try {
			// 공지사항 정보 상세 조회
			outBoardVO = boardService.getBoardDetail(inBoardVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("outBoardVO", outBoardVO);
		model.addAttribute("attIdFileBox", fileUploadUtilService.createFileControl("첨부파일", "attId", commonUtilService.isEmptyCheck(outBoardVO) ? "" :  outBoardVO.getAttId(), true, "Left", "49%"));
		model.addAttribute("saveMode", pSaveMode);
		
		return "Board/BoardWrite";
	}
	
	@PostMapping("/BoardWrite.do")
	public String BoardWrite(@RequestParam(value="attIdSeq", required=false) List<String> attIdSeqs, @RequestPart List<MultipartFile> attIdFiles, BoardVO vo) {
		logger.info("/Board/BoardWrite.do");

		try {
			String attId = "";
			
			// 첨부파일 생성
			if (commonUtilService.isEmptyCheck(vo.getAttId())) {
				attId = fileUploadUtilService.createFiles(attIdFiles, "BOARD", vo.getRegUserId());
			}
			// 첨부파일 수정
			else {
				fileUploadUtilService.saveFiles(vo.getAttId(), attIdSeqs, attIdFiles, "BOARD", vo.getRegUserId());
				/*
				 * else { 
				 * 		fileUploadUtilService.saveFiles(vo.getAttId(), attIdSeqs.toArray(new String[attIdSeqs.size()]), attIdFiles, "PROPOSAL", vo.getEmpNo());*/
			}

			if (vo.getSaveMode().equals("C")) {
				vo.setAttId(attId);
				boardService.createBoardWrite(vo);	
			}
			else {
				boardService.updateBoardWrite(vo);	
			}			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		return "Board/ListBoard";
	}
	
}

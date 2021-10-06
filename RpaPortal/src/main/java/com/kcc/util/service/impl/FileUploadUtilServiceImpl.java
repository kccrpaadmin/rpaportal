package com.kcc.util.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.runtime.directive.Foreach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kcc.biz.model.AttFileVO;
import com.kcc.biz.service.IAttFileService;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.IFileUploadUtilService;
import com.kcc.words.ConstWord;
import com.markany.nt.WDSCryptAll;

public class FileUploadUtilServiceImpl implements IFileUploadUtilService {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtilServiceImpl.class);
	
	// 업로드 제한 용량 (code-properties.xml)
	@Value("#{codeProperties['limitUploadSize']}")
	private String limitUploadSize;
		
	// 서버 업로드 폴더 위치 (code-properties.xml)
	@Value("#{codeProperties['uploadPath']}")
	private String uploadPath;
	
	// 로컬 업로드 폴더 위치 (code-properties.xml)
	@Value("#{codeProperties['uploadLocalPath']}")
	private String uploadLocalPath;
	
	@Resource(name="commonUtilService")
	private ICommonUtilService commonUtilService;
	
	@Resource(name="attFileService")
	private IAttFileService attFileService;
	
	// 첨부파일 생성
	public String createFiles(List<MultipartFile> files, String menuId, String empNo) throws Exception {
		// 첨부ID
		String attId = "";
		
		double fileSizeSum = 0;
		double maxLimitUploadSize = Double.parseDouble(limitUploadSize);
		
		// 첨부파일 총 용량 합계
		for (MultipartFile file : files) {
			fileSizeSum = fileSizeSum + file.getSize();
		}
		
		// 저장 데이터가 없는 경우
		if (fileSizeSum <= 0) {
			return attId;
		}
		
		// 최대 첨부 용량을 초과한 경우
		if (fileSizeSum > maxLimitUploadSize) {
			throw new Exception(ConstWord.FILE_MAXSIZE_ERROR);
		}
		
		String uploadFilePath = uploadPath;
		String uploadLocalFilePath = uploadLocalPath;
		
		// 년월일시분초.밀리세컨드
		attId = menuId + "-" + new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date()) + Integer.toString(getRandomRange(1000, 9999));
		Calendar cal = Calendar.getInstance();
		String yearMon = cal.get(Calendar.YEAR) + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String saveUploadFilePath = uploadFilePath + File.separator + menuId + File.separator + yearMon;
		String saveFilePath = menuId + File.separator +  yearMon;
		String saveUploadLocalFilePath = uploadLocalFilePath + File.separator + File.separator + menuId + File.separator + File.separator + yearMon;
		String saveLocalFilePath = menuId + File.separator + File.separator +  yearMon;
		
		logger.info(attId);
		
		// 저장 디렉토리 생성
		saveUploadFilePath = createfolder(saveUploadFilePath);
		saveUploadLocalFilePath = createfolder(saveUploadLocalFilePath);

		// 첨부 VO 생성
		AttFileVO inAttFileVO = new AttFileVO();
		inAttFileVO.setAttId(attId);
		inAttFileVO.setMenuId(menuId);
		inAttFileVO.setEmpNo(empNo);
		
		int seq = 0;
		
		// 첨부파일 목록 VO 생성
		List<AttFileVO> inListAttFileVO = new ArrayList<AttFileVO>();
		
		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				// 파일 순번
				seq++;
				
				// 원본 파일이름 및 확장자
				String orgFileNm = file.getOriginalFilename();
				String fileNm = FilenameUtils.getBaseName(file.getOriginalFilename());
				String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
	
				// 랜덤 유니크 값 생성
				String strDate = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
				String uid = Integer.toString(getRandomRange(1000, 9999));
				
				// 원본이름 + 년월일 + 랜덤값
				String saveFileNm = fileNm + "_" + strDate + uid + "." + fileExt;
	
				// 파일 로컬 드라이브 저장
				File fileData = new File(saveUploadLocalFilePath, saveFileNm);
				file.transferTo(fileData);
				
				// 파일 복호화
				WDSCryptAll cryptAll = null;
				cryptAll = new WDSCryptAll();
				
				String saveUploadLocalFileFullPath = saveUploadLocalFilePath + File.separator + File.separator + saveFileNm;
				
				// 물리 드라이브 경로 확인
				logger.info(saveUploadLocalFileFullPath);
				
				cryptAll.sSourceFilePath = saveUploadLocalFileFullPath;			
				
				int chkRs = cryptAll.iEncCheck();
				
				// 암호화 여부 체크
				logger.info(Integer.toString(chkRs));
				
				if (chkRs == 1 || chkRs == 2) {
					int decRs = cryptAll.iDecrypt();
					logger.info("파일 복호화 결과 : " + decRs);

					// 복호화 실패
					if (decRs != 0) {
						fileData.delete();
						throw new Exception("파일 복호화 오류가 발생 하였습니다.");
		            }					
				}
				
				// 파일 이동
				/*
				 * 네트워크 드라이브로 이동 안됨
				Path srcPath = Paths.get(saveUploadLocalFileFullPath);
				Path dstPath = Paths.get(saveUploadFilePath + File.separator + saveFileNm);
				Files.move(srcPath, dstPath, StandardCopyOption.ATOMIC_MOVE);
				*/
				
				logger.info(saveUploadLocalFileFullPath);
				logger.info(saveUploadFilePath + File.separator + saveFileNm);
				
				File srcFile = new File(saveUploadLocalFileFullPath);
				File dstFile = new File(saveUploadFilePath + File.separator + saveFileNm);
				srcFile.renameTo(dstFile);
				
				// 첨부파일 목록 VO 삽입
				AttFileVO attFileVO = new AttFileVO();
				attFileVO.setAttId(attId);
				attFileVO.setSeq(Integer.toString(seq));
				attFileVO.setFilePath(saveFilePath);
				attFileVO.setFileNm(orgFileNm);
				attFileVO.setSaveFileNm(saveFileNm);
				attFileVO.setFileSize(Long.toString(file.getSize()));
				attFileVO.setEmpNo(empNo);
				inListAttFileVO.add(attFileVO);
			}
		}
		
		// 저장 - 오류 발생시 서비스단에서 롤백처리
		attFileService.createAttAndAttFiles(inAttFileVO, inListAttFileVO);
		
		return attId;
	}
	
	// 첨부파일 수정
	public void saveFiles(String attId, List<String> seqs, List<MultipartFile> files, String menuId, String empNo) throws Exception {
		// 첨부파일 입력
		AttFileVO inAttFileVO = new AttFileVO();
		inAttFileVO.setAttId(attId);
		
		// 첨부파일 출력 
		List<AttFileVO> outListAttFileVO = new ArrayList<AttFileVO>();
		
		// 첨부파일 목록 조회
		outListAttFileVO = attFileService.listAttFile(inAttFileVO);
		
		// 삭제 대상 순번 목록
		StringBuilder deleteSb =  new StringBuilder("");
		
		int i = 0;
		int maxSeq = 0;
		
		// 삭제대상 순번 목록에 삽입
		for (AttFileVO attFileVO : outListAttFileVO) {
			if (commonUtilService.isEmptyCheck(seqs) || seqs.contains(attFileVO.getSeq()) == false) {
				if (i == 0) {
					deleteSb.append(attFileVO.getSeq());	
				}
				else {
					deleteSb.append("," + attFileVO.getSeq());
				}
				i++;
			}
			
			// 최종 순번
			maxSeq = Integer.parseInt(attFileVO.getSeq());
		}
		
		// 삭제 대상이 있는 경우
		if (commonUtilService.isEmptyCheck(deleteSb.toString()) == false) {
			inAttFileVO.setSeqStr(deleteSb.toString());
			attFileService.deleteAttFile(inAttFileVO);
		}
		
		// 파일 저장
		double fileSizeSum = 0;
		double maxLimitUploadSize = Double.parseDouble(limitUploadSize);
		
		// 첨부파일 총 용량 합계
		for (MultipartFile file : files) {
			fileSizeSum = fileSizeSum + file.getSize();
		}
		
		// 최대 첨부 용량을 초과한 경우
		if (fileSizeSum > maxLimitUploadSize) {
			throw new Exception(ConstWord.FILE_MAXSIZE_ERROR);
		}
		
		String uploadFilePath = uploadPath;
		String uploadLocalFilePath = uploadLocalPath;
		
		// 년월일시분초.밀리세컨드
		Calendar cal = Calendar.getInstance();
		String yearMon = cal.get(Calendar.YEAR) + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String saveUploadFilePath = uploadFilePath + File.separator + menuId + File.separator + yearMon;
		String saveFilePath = menuId + File.separator +  yearMon;
		String saveUploadLocalFilePath = uploadLocalFilePath + File.separator + File.separator + menuId + File.separator + File.separator + yearMon;
		String saveLocalFilePath = menuId + File.separator + File.separator +  yearMon;
		
		logger.info(attId);
		
		// 저장 디렉토리 생성
		saveUploadFilePath = createfolder(saveUploadFilePath);
		saveUploadLocalFilePath = createfolder(saveUploadLocalFilePath);

		int seq = maxSeq;
		
		// 첨부파일 목록 VO 생성
		List<AttFileVO> inListAttFileVO = new ArrayList<AttFileVO>();
		
		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				// 파일 순번
				seq++;
				
				// 원본 파일이름 및 확장자
				String orgFileNm = file.getOriginalFilename();
				String fileNm = FilenameUtils.getBaseName(file.getOriginalFilename());
				String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
	
				// 랜덤 유니크 값 생성
				String strDate = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
				String uid = Integer.toString(getRandomRange(1000, 9999));
				
				// 원본이름 + 년월일 + 랜덤값
				String saveFileNm = fileNm + "_" + strDate + uid + "." + fileExt;
	
				// 파일 로컬 드라이브 저장
				File fileData = new File(saveUploadLocalFilePath, saveFileNm);
				file.transferTo(fileData);
				
				// 파일 복호화
				WDSCryptAll cryptAll = null;
				cryptAll = new WDSCryptAll();
				
				String saveUploadLocalFileFullPath = saveUploadLocalFilePath + File.separator + File.separator + saveFileNm;
				
				// 물리 드라이브 경로 확인
				logger.info(saveUploadLocalFileFullPath);
				
				cryptAll.sSourceFilePath = saveUploadLocalFileFullPath;			
				
				int chkRs = cryptAll.iEncCheck();
				
				// 암호화 여부 체크
				logger.info(Integer.toString(chkRs));
				
				if (chkRs == 1 || chkRs == 2) {
					int decRs = cryptAll.iDecrypt();
					logger.info("파일 복호화 결과 : " + decRs);

					// 복호화 실패
					if (decRs != 0) {
						fileData.delete();
						throw new Exception("파일 복호화 오류가 발생 하였습니다.");
		            }					
				}
				
				// 파일 이동
				/*
				 * 네트워크 드라이브로 이동 안됨
				Path srcPath = Paths.get(saveUploadLocalFileFullPath);
				Path dstPath = Paths.get(saveUploadFilePath + File.separator + saveFileNm);
				Files.move(srcPath, dstPath, StandardCopyOption.ATOMIC_MOVE);
				*/
				
				logger.info(saveUploadLocalFileFullPath);
				logger.info(saveUploadFilePath + File.separator + saveFileNm);
				
				File srcFile = new File(saveUploadLocalFileFullPath);
				File dstFile = new File(saveUploadFilePath + File.separator + saveFileNm);
				srcFile.renameTo(dstFile);
				
				// 첨부파일 목록 VO 삽입
				AttFileVO attFileVO = new AttFileVO();
				attFileVO.setAttId(attId);
				attFileVO.setSeq(Integer.toString(seq));
				attFileVO.setFilePath(saveFilePath);
				attFileVO.setFileNm(orgFileNm);
				attFileVO.setSaveFileNm(saveFileNm);
				attFileVO.setFileSize(Long.toString(file.getSize()));
				attFileVO.setEmpNo(empNo);
				inListAttFileVO.add(attFileVO);
			}
		}
		
		// 저장 - 오류 발생시 서비스단에서 롤백처리
		attFileService.createAttFiles(inListAttFileVO);		
	}
	
	// 첨부폴더 생성
	private String createfolder(String folderPath) {
		if (new File(folderPath).exists()) {
			return folderPath;
		}
		else {
			File dirPath = new File(folderPath);
			dirPath.mkdirs(); 
		}
		return folderPath;
	}

	// 4자리 랜덤 숫자 생성
	private int getRandomRange(int startData, int endData) {
	    return (int) (Math.random() * (endData - startData + 1)) + startData;
	}
	
	// 첨부파일 박스 Html 생성
	public String createFileControl(String title, String id, String attId, Boolean editable, String pos, String width) {
		AttFileVO inAttFileVO = new AttFileVO();
		List<AttFileVO> outListAttFileVO = new ArrayList<AttFileVO>();
		inAttFileVO.setAttId(attId);
			
		try {
			outListAttFileVO = attFileService.listAttFile(inAttFileVO);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
		
		StringBuilder sb =  new StringBuilder("");
		
		// 수정이 가능한 경우
		if (editable) {
			sb.append("<div class='file_control' style='float:" + pos + ";width:" + width + "'>");
			sb.append("<input type='hidden' name='" + id + "' value='" + attId + "'>");
			sb.append("<div class='file_header'>");
			sb.append("<div class='file_header_left'>" + title + "</div>");
			sb.append("<div class='file_header_right'><a class='btn_common3' id='btn_add_" + id + "'>파일추가</a></div>");
			sb.append("</div>");
			sb.append("<div class='file_body'>");
			sb.append("<h1 class='file_h1'>파일명</h1>");
			sb.append("<div class='file_body_scroll'>");
			sb.append("<table class='file_tbl'>");
			sb.append("<colgroup>");
			sb.append("<col width='70%' />");
			sb.append("<col width='' />");
			sb.append("</colgroup>");
			sb.append("<tbody id='file_tbody_" + id + "'>");
			
			for (AttFileVO attFileVO : outListAttFileVO) {
				sb.append("<tr>");
				sb.append("<td class='file_tbl_td_l'>");
				sb.append("<a href='/FileDownload/Download.do?attId=" + attFileVO.getAttId() + "&seq=" + attFileVO.getSeq() + "'>" + attFileVO.getFileNm() + "</a>");
				sb.append("<input type='hidden' name='" + id + "Seq' value='" + attFileVO.getSeq() + "' />");
				sb.append("</td>");
				sb.append("<td class='file_tbl_td_r'>");
				sb.append("<a class='btn_common4'>파일삭제</a>");
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
			sb.append("</table>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
			
			sb.append("<script type='text/javascript'>");
			sb.append("$(document).on('click', '#btn_add_" + id + "', function (e) {");
			sb.append("$('#file_tbody_" + id + "').append('<tr><td class=\"file_tbl_td_l\"><input type=\"file\" name=\"" + id + "Files\" /></td><td class=\"file_tbl_td_r\"><a class=\"btn_common4\" id=\"btn_delete_" + id + "\">파일삭제</a></td></tr>');");
			sb.append("});");
			sb.append("$(document).on('click', '#file_tbody_" + id + " .btn_common4', function (e) {");
			sb.append("$(this).parent().parent().remove();");
			sb.append("});");
			sb.append("</script>");
		}
		else {
			sb.append("<div class='file_control' style='float:" + pos + ";width:" + width + "'>");
			sb.append("<input type='hidden' name='" + id + "' value='" + attId + "'>");
			sb.append("<div class='file_header'>");
			sb.append("<div class='file_header_left' colspan='2'>" + title + "</div>");
			sb.append("</div>");
			sb.append("<div class='file_body'>");
			sb.append("<h1 class='file_h1'>파일명</h1>");
			sb.append("<div class='file_body_scroll'>");
			sb.append("<table class='file_tbl'>");
			sb.append("<colgroup>");
			sb.append("<col width='70%' />");
			sb.append("<col width='' />");
			sb.append("</colgroup>");
			sb.append("<tbody id='file_tbody_" + id + "'>");
			
			for (AttFileVO attFileVO : outListAttFileVO) {
				sb.append("<tr>");
				sb.append("<td class='file_tbl_td_l' colspan='2'>");
				sb.append("<a href='/FileDownload/Download.do?attId=" + attFileVO.getAttId() + "&seq=" + attFileVO.getSeq() + "'>" + attFileVO.getFileNm() + "</a>");
				sb.append("<input type='hidden' name='" + id + "Seq' value='" + attFileVO.getSeq() + "' />");
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
			sb.append("</table>");
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
		}

		return sb.toString();
	}
}
package com.kcc.controller.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.file.Files;
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
import org.apache.commons.io.FileUtils;
import org.jasypt.commons.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.AccessVO;
import com.kcc.biz.model.AttFileVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.model.UserVO;
import com.kcc.biz.service.IAccessService;
import com.kcc.biz.service.IAttFileService;
import com.kcc.biz.service.ILoginService;
import com.kcc.biz.service.IMenuService;
import com.kcc.biz.service.IUserService;
import com.kcc.controller.base.BaseController;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.IRouteUtilService;

@RequestMapping("/FileDownload")
@Controller
public class FileDownloadController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);
	
	// 서버 업로드 폴더 위치 (code-properties.xml)
	@Value("#{codeProperties['uploadPath']}")
	private String uploadPath;
	
	@Resource(name="attFileService")
	IAttFileService attFileService;
	
	@GetMapping("/Download.do")
	public void Download(AttFileVO vo, HttpServletResponse response) {
		logger.info("/FileDownload/Download.do");
		
		try {
			AttFileVO attFileVO = attFileService.getAttFile(vo);
			String oriFileNm = attFileVO.getFileNm();
			String filePath = attFileVO.getFilePath();
			
			// 2021.08.19 아이비시트 파일 다운로드 추가후, commons-io 충돌로 인한 수정
			// byte fileByte[] = FileUtils.readFileToByteArray(new File(uploadPath + "\\" + filePath));
			File file = new File(uploadPath + "\\" + filePath);
	        byte[] fileByte = Files.readAllBytes(file.toPath());
	        response.setHeader("Content-Type", "application/octet-stream");
	        response.setHeader("Content-Transfer-Encoding", "binary"); 
	        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(oriFileNm, "UTF-8")+"\";");
	        response.setHeader("Content-Length", Long.toString(fileByte.length));
	        response.setHeader("Pragma", "no-cache;");
	        response.setHeader("Expires", "-1;");
	        response.getOutputStream().write(fileByte);
	        response.getOutputStream().flush();
			response.getOutputStream().close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
	}	
}

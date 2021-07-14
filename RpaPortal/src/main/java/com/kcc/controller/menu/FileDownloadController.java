package com.kcc.controller.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
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
			/*
			//AttFileVO attFileVO = attFileService.getAttFile(vo);
			//String oriFileNm = attFileVO.getFileNm();
			//String filePath = attFileVO.getFilePath();
			
			//byte fileByte[] = FileUtils.readFileToByteArray(new File(uploadPath + "\\" + filePath));
			byte fileByte[] = FileUtils.readFileToByteArray(new File("X:\\AttFiles\\RA004001\\202107\\150126000001_202107051339131609610.pdf"));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(fileByte.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("test.pdf","UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.getOutputStream().write(fileByte);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			*/
			
	        String saveFileName = "\"X:\\\\AttFiles\\\\RA004001\\\\202107\\\\150126000001_202107051339131609610.pdf\"".toString();
	        // saveFileName을 만든다.
	        
	        String contentType = "application/octet-stream";
	        // contentType 가져오고
	 
	        File file = new File(saveFileName);
	        long fileLength = file.length();
	 
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + "test.pdf" + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary"); 
	        response.setHeader("Content-Type", contentType);
	        response.setHeader("Content-Length", "" + fileLength);
	        response.setHeader("Pragma", "no-cache;");
	        response.setHeader("Expires", "-1;");
	        // 그 정보들을 가지고 reponse의 Header에 세팅한 후
	        
	        try (FileInputStream fis = new FileInputStream(saveFileName); OutputStream out = response.getOutputStream();) {
	            // saveFileName을 파라미터로 넣어 inputStream 객체를 만들고 
	            // response에서 파일을 내보낼 OutputStream을 가져와서  
	            int readCount = 0;
	            byte[] buffer = new byte[1024];
	            // 파일 읽을 만큼 크기의 buffer를 생성한 후 
	            while ((readCount = fis.read(buffer)) != -1) {
	                out.write(buffer, 0, readCount);
	                // outputStream에 씌워준다
	            }
	        } catch (Exception ex) {
	            throw new RuntimeException("file Load Error");
	        }
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}

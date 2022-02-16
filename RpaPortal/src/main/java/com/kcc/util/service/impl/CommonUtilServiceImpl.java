package com.kcc.util.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Base64.Encoder;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.AttFileVO;
import com.kcc.biz.model.BotScheduleVO;
import com.kcc.biz.model.CodeVO;
import com.kcc.biz.model.MenuVO;
import com.kcc.biz.service.IAttFileService;
import com.kcc.biz.service.ICodeService;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.words.ConstWord;

@Component("commonUtilService")
public class CommonUtilServiceImpl implements ICommonUtilService {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtilServiceImpl.class);
	
	/*
	 * VO의 null 확인
	 * MenuVO menuVO = new MenuVO();
	 * 모델의 경우, 선언시에는 null이 아니지만 SP에서 0개행을 삽입할 경우 null이 됨
	 * 모델의 경우, 선언 자체가 get/set을 할수있기 때문에 null 판단 불가능
	 * List<MenuVO> listMenuVO = new ArrayList<MenuVO>();
	 * 모델 리스트의 경우, 선언시에는 행의 개수가 0개 SP에서 0개 행을 삽입할 경우 그대로 0개
	 * 선언 자체를 null로 할 경우, get/set를 할 수 없음
    */
	
	/*
	 * VO의 복제 BeanUtils
	 * MenuVO menuVO = new MenuVO();
	 * menuVO.setMenuId("RA001");
	 * Map<String, Object> map = new HashMap<String, Object>();
	 * map = BeanUtils.describe(menuVO);
	 * logger.info(map.get("menuId").toString());
	*/
	
	@Resource(name="codeService")
	private ICodeService codeService;
	
	// 객체 비어 있는지 확인
	public Boolean isEmptyCheck(Object obj) {
		boolean returnVal = false;
		
		// VO의 경우, 이곳에서 검증됨 (String, List, Map, 배열)
		if (obj == null) {
			returnVal = true;
		}
		else if (obj instanceof String && "".equals(obj.toString().trim())) {
			returnVal = true;
		}
		else if (obj instanceof List && ((List) obj).isEmpty()) {
			returnVal = true;
		}
		else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			returnVal = true;
		}
		else if (obj instanceof Object[] && Array.getLength(obj) == 0) {
			returnVal = true;
		}

		return returnVal;
	}
	
	// SHA256 암호화 데이터 조회
	public String getSHA256(String pwd) {
		String toReturn = null;
		try {
		    MessageDigest digest = MessageDigest.getInstance("SHA-256");
		    digest.reset();
		    digest.update(pwd.getBytes("UTF-8"));
		    toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
		
		return toReturn;
    }
	
	// 접속 아이피 조회
	public String getClientIp(HttpServletRequest req) {
		String clientIp = null; 
		clientIp = req.getHeader("X-Forwarded-For");
		
		if (clientIp == null || "".equals(clientIp)) {
            clientIp = req.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || "".equals(clientIp)) {
            clientIp = req.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || "".equals(clientIp)) {
            clientIp = req.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIp == null || "".equals(clientIp)) {
            clientIp = req.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIp == null || "".equals(clientIp)) {
            clientIp = req.getRemoteAddr();
        }

		return clientIp;
    }
	
	// 접속 아이피 조회
	public String getLocalHostIp() {
		String localHostIp = "";
		try {
			InetAddress local = InetAddress.getLocalHost();
			localHostIp = local.getHostAddress();
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return localHostIp;
    }
		
	// 접속 디바이스 조회
	public String getDeviceType(HttpServletRequest req) {
		String deviceType = "unknown";
		Device device = null;
		device = DeviceUtils.getCurrentDevice(req);
        
        if (device != null && device.isNormal()) {
            deviceType = "pc";
        } 
        else if (device != null && device.isMobile()) {
            deviceType = "mobile";
        } 
        else if (device != null && device.isTablet()) {
            deviceType = "tablet";
        }

		return deviceType;
    }
	
	// 사용자 인증정보 조회
	public String getCertInfo() {
		// 사용자 정보 조회
		UseCustomUserDetails userDetails = (UseCustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		Gson gson = new Gson();
		JsonObject object = new JsonObject();
		object.addProperty("roleType", userDetails.getRoleType());
		object.addProperty("userId", userDetails.getUserId());
		object.addProperty("pwd", userDetails.getPwd());
		object.addProperty("userNm", userDetails.getUserNm());
		object.addProperty("email", userDetails.getEmail());
		object.addProperty("deptCd", userDetails.getDeptCd());
		object.addProperty("deptNm", userDetails.getDeptNm());
		object.addProperty("titleCd", userDetails.getTitleCd());
		object.addProperty("titleNm", userDetails.getTitleNm());
		object.addProperty("dutyCd", userDetails.getDutyCd());
		object.addProperty("dutyNm", userDetails.getDutyNm());
		object.addProperty("empNo", userDetails.getEmpNo());
		object.addProperty("compTel", userDetails.getCompTel());
		object.addProperty("compAddr", userDetails.getCompAddr());
		object.addProperty("tel", userDetails.getTel());
		object.addProperty("mobile", userDetails.getMobile());
		object.addProperty("addr", userDetails.getAddr());
		object.addProperty("userSignUrl", userDetails.getUserSignUrl());
		object.addProperty("userPhotoUrl", userDetails.getUserPhotoUrl());
		object.addProperty("jobNm", userDetails.getJobNm());
		object.addProperty("dbServer", userDetails.getDbServer());
		String certInfo = gson.toJson(object);
		
		return certInfo;
    }
	
	// 개발, 운영 여부 조회
	public String getServerEnv() {
		String serverEnv = "Local1";
		
		if (ConstWord.PORTAL_REAL_IP.equals(getLocalHostIp())) {
			serverEnv = "Real";
		}
		else if (ConstWord.PORTAL_DEV_IP.equals(getLocalHostIp())) {
			serverEnv = "Dev";
		}
		else if (ConstWord.PORTAL_LOCAL2_IP.equals(getLocalHostIp())) {
			serverEnv = "Local2";
		}
		
		return serverEnv;
    }
	
	// 파일 이름 조회
	public String getFileNm(String filePath) {
		File file = new File(filePath);
		String fileName = file.getName();
		int pos = fileName .lastIndexOf(".");
		String fileNm = fileName.substring(0, pos);
		return fileNm;
	}

	// 파일 확장자 조회
	public String getFileExtension(String filePath) {
		File file = new File(filePath);
		String fileName = file.getName();
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
		return fileExtension;
	}
	
	// 파일을 바이트로 변환 후, 조회
	public byte[] getExtractFileBytes(String filePath) {
		byte[] fileArray = null;
		try {
			File path = new File(filePath);
			FileInputStream fis = new FileInputStream(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = fis.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}
			fileArray = baos.toByteArray();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return fileArray;
    }
	
	// 바이트 데이터를 Base64로 변환 후, 조회
	public byte[] getEncodingBase64Bytes(byte[] targetBytes) {
		Encoder encoder = Base64.getEncoder();
		return encoder.encode(targetBytes);
	}
	
	// Ocr Naver 요청 데이터 조회
	public JSONObject getOcrNaverRequestJsonData(String menuId, String filePath) {
		// 파일 이름 및 확장자 조회 
		String fileNm = getFileNm(filePath);
		String fileExtension = getFileExtension(filePath);
		
		// 이미지를 바이트로 변환
		byte[] imageBytes = getExtractFileBytes(filePath);
		byte[] base64Bytes = getEncodingBase64Bytes(imageBytes);
		
		// 타임 스탬프 조회
		String timeStamp = String.valueOf(Calendar.getInstance().getTime().getTime());
		
		// Ocr Naver API Request 정보 정의
		int[] arrBusinessLicense = {5494,5495};
		
		JSONArray inTemplateIdsJsonArray = new JSONArray();
		if ("RA003002".equals(menuId)) {
			for (int i = 0; i < arrBusinessLicense.length; i++) {
				inTemplateIdsJsonArray.add(arrBusinessLicense[i]);				
			}
		}
		
        JSONObject inImagesJsonObject = new JSONObject();
        inImagesJsonObject.put("name", fileNm);
        inImagesJsonObject.put("format", fileExtension);
        inImagesJsonObject.put("data", new String(base64Bytes));
        inImagesJsonObject.put("templateIds", inTemplateIdsJsonArray);
        
        JSONArray inImagesJsonArray = new JSONArray();
        inImagesJsonArray.add(inImagesJsonObject);
        
        JSONObject inDataJsonObject = new JSONObject();
        inDataJsonObject.put("version", "v2");
        inDataJsonObject.put("requestId", timeStamp);
        inDataJsonObject.put("timestamp", timeStamp);
        inDataJsonObject.put("images", inImagesJsonArray);
        
        return inDataJsonObject;
	}
	
	// 사용자 지정 날짜 정보 조회
	public String getDateUserFormat(String formatType, String dateType, int controlDate) {
		String dateFormat = "";
		
		DateFormat df = new SimpleDateFormat(formatType);
		Calendar cal = Calendar.getInstance( );
        
        if ("Year".equals(dateType)) {
        	cal.add(cal.YEAR, controlDate);
        	dateFormat = df.format(cal.getTime());
        }
        else if ("Month".equals(dateType)) {
        	cal.add(cal.MONTH, controlDate);
        	dateFormat = df.format(cal.getTime());
        }
        
		return dateFormat;
    }
	
	// 공통코드 목록 조회 (프로시져 지정)
	public String getCodeProcedureSelectBox(String selectBoxNm, String procedureNm, String cd, Boolean isAddFirstRow, String addFirstRowNm, String selectedCd) {
		CodeVO vo = new CodeVO();
		vo.setProcedureNm(procedureNm);
		vo.setCd(cd);
		
		List<CodeVO> listCodeVO = new ArrayList<CodeVO>();
		
		try {
			listCodeVO = codeService.listCodeProcedure(vo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuilder sb =  new StringBuilder();
		sb.append("<select id='" + selectBoxNm + "' name='" + selectBoxNm + "' class='combo_every'>");
		
		if (isAddFirstRow) {
			if (addFirstRowNm.equals(selectedCd)) {
				sb.append("<option value='" + addFirstRowNm + "' selected='selected'>" + addFirstRowNm + "</option>");	
			}
			else {
				sb.append("<option value='" + addFirstRowNm + "'>" + addFirstRowNm + "</option>");
			}
		}
		
		for (CodeVO codeVO : listCodeVO) {
			if (codeVO.getCd().equals(selectedCd)) {
				sb.append("<option value='" + codeVO.getCd() + "' selected='selected'>" + codeVO.getCdNm() + "</option>");
			}
			else {
				sb.append("<option value='" + codeVO.getCd() + "'>" + codeVO.getCdNm() + "</option>");
			}
		}

		sb.append("</select>");
		
		return sb.toString();
	}
	
	// 그리드 공통코드 목록 조회 (공통 프로시져)
	public String getGridCodeCombo(String upCd, String colNm) {
		CodeVO inCodeVO = new CodeVO();		
		inCodeVO.setUpCd(upCd);
		
		// CodeVO 출력
		CodeVO outCodeVO = new CodeVO();
		String returnValue = "";
				
		try {
			outCodeVO = codeService.getGridCodeCombo(inCodeVO);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(colNm.equals("Cd")) {
			returnValue = outCodeVO.getCd();
		}
		else	{
			returnValue = outCodeVO.getCdNm();
		}
				
		return returnValue;
	}
	
}
package com.kcc.util.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.protobuf.ByteString;
import com.kcc.auth.UseCustomUserDetails;
import com.kcc.biz.model.CrawlRunVO;
import com.kcc.biz.model.StatusMapVO;
import com.kcc.biz.model.StatusVO;
import com.kcc.util.service.ICommonUtilService;
import com.kcc.util.service.ICrawlUtilService;
import com.kcc.util.service.IOcrApiUtilService;
import com.kcc.words.ConstWord;

@Component("ocrApiUtilService")
public class OcrApiUtilServiceImpl implements IOcrApiUtilService {
	private static final Logger logger = LoggerFactory.getLogger(OcrApiUtilServiceImpl.class);
	
	// 서버 업로드 폴더 위치 (code-properties.xml)
	@Value("#{codeProperties['uploadPath']}")
	private String uploadPath;
	
	@Resource(name="commonUtilService")
	ICommonUtilService commonUtilService;
	
	public StatusVO requestOcrVision(String filePath) {
		String status = "Fail";
		String errorMsg = "";
		String data = "";

		try {
			String uploadFilePath = uploadPath;
			
			String imageFilePath = uploadFilePath + filePath;
			
			List<AnnotateImageRequest> requests = new ArrayList<>();
			ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));

			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);

			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
				BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			    List<AnnotateImageResponse> responses = response.getResponsesList();
			    
			    for (AnnotateImageResponse res : responses) {
			    	if (res.hasError()) {
			    		errorMsg = res.getError().getMessage();
			    		break;
			    	}
			    	else {
			    		data = res.getTextAnnotationsList().get(0).getDescription();
				    	status = "Success";	
			    	}
			    }
			}
			catch(Exception e1) {
				errorMsg = e1.getMessage();
				e1.printStackTrace();
			}
		}
		catch(Exception e2) {
			errorMsg = e2.getMessage();
			e2.printStackTrace();
		}
		
		StatusVO statusVO = new StatusVO();
		statusVO.setStatus(status);
		statusVO.setErrorMsg(errorMsg);
		statusVO.setData(data);
		
		return statusVO;
	}
	
	public StatusMapVO requestOcrNaverTemp(String menuId, String filePath) {
		String status = "Fail";
		String errorMsg = "";
		Map mapData = new HashMap<String, Object>();

		try {
			String uploadFilePath = uploadPath;
			
			String imageFilePath = uploadFilePath + filePath;
			
			// Ocr Naver 요청 데이터 조회
			JSONObject inDataJsonObject = new JSONObject();
			inDataJsonObject = commonUtilService.getOcrNaverRequestJsonData(menuId, imageFilePath);
			
			// HttpURLConnection 정의 
			HttpURLConnection conn = null;
			
	        // URL 설정
	        URL url = new URL(ConstWord.OCR_NAVER_TEMP_API_URL);
	        conn = (HttpURLConnection) url.openConnection();
	        
	        // Request 형식 설정
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	        conn.setRequestProperty("X-OCR-SECRET", ConstWord.OCR_NAVER_TEMP_API_KEY);
	        
	        // Request에 JSON data 준비
	        conn.setDoOutput(true);
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	        
	        // Request에 쓰기
	        bw.write(inDataJsonObject.toString());
	        bw.flush();
	        bw.close();
	        
	        // 보내고 결과값 받기
	        int responseCode = conn.getResponseCode();
	        
	        // 성공인 경우
	        if (responseCode == 200) {
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	            StringBuilder sb = new StringBuilder();
	            String line = "";
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	            
	            JSONParser jsonParser = new JSONParser();
	            JSONObject outDataJsonObject = (JSONObject) jsonParser.parse(sb.toString());
	            
	            // images 배열 데이터
	            JSONArray imagesJsonArray = (JSONArray) outDataJsonObject.get("images");
	            
	            // images 배열[0]의 Json 데이터
	            JSONObject imagesJsonObject = (JSONObject) imagesJsonArray.get(0);
	            logger.info("uid : " + imagesJsonObject.get("uid").toString());
	            logger.info("name : " + imagesJsonObject.get("name").toString());
	            logger.info("inferResult : " + imagesJsonObject.get("inferResult").toString());
	            logger.info("message : " + imagesJsonObject.get("message").toString());
	            
	            if (imagesJsonObject.get("inferResult").toString().equals("FAILURE")) {
	            	errorMsg = imagesJsonObject.get("message").toString();
	            }
	            else {
	            	 // matchedTemplate의 Json 데이터
		            JSONObject matchedTemplateJsonObject = (JSONObject) imagesJsonObject.get("matchedTemplate");
		            logger.info("matchedTemplate id : " + matchedTemplateJsonObject.get("id").toString());
		            logger.info("matchedTemplate name : " + matchedTemplateJsonObject.get("name").toString());
		            
		            mapData.put("TemplateId", matchedTemplateJsonObject.get("id").toString());
		            mapData.put("TemplateNm", matchedTemplateJsonObject.get("name").toString());
		            
		            // fields 배열 데이터 
		            JSONArray fieldsJsonArray = (JSONArray) imagesJsonObject.get("fields");
		            for (int i = 0; i < fieldsJsonArray.size(); i++) {
		            	JSONObject fieldsJsonObject = (JSONObject) fieldsJsonArray.get(i);
		            	logger.info("fields name : " + fieldsJsonObject.get("name").toString());
		            	logger.info("fields inferText : " + fieldsJsonObject.get("inferText").toString());
		            	
		            	mapData.put(fieldsJsonObject.get("name").toString(), fieldsJsonObject.get("inferText").toString());
					}
		            
		            status = "Success";
	            }
	        }
	        else if (responseCode == 400) {
	        	logger.info("400");
	        	errorMsg = "400";
	        }
	        else if (responseCode == 401) {
	        	logger.info("401");
	        	errorMsg = "401";
	        }
	        else if (responseCode == 500) {
	        	logger.info("500");
	        	errorMsg = "500";
	        }	        
		}
		catch(Exception e) {
			errorMsg = e.getMessage();
			e.printStackTrace();
		}
		
		StatusMapVO statusMapVO = new StatusMapVO();
		statusMapVO.setStatus(status);
		statusMapVO.setErrorMsg(errorMsg);
		statusMapVO.setMapData(mapData);
	
		return statusMapVO;
	}
}
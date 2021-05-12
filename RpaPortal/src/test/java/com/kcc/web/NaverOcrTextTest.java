package com.kcc.web;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Base64;
import java.util.Base64.Encoder;

import net.sf.json.JSONException;


public class NaverOcrTextTest {

	public static void main(String[] args) {
		String status = "Fail";
				
		HttpURLConnection conn = null;
	    JSONObject responseJson = null;
	    
	    try {
	        //URL 설정
	        URL url = new URL("https://8c4e1d2252484597b2955d676b38bd3b.apigw.ntruss.com/custom/v1/5236/6b5f98744e2e4961f8bf96829e31d32f70838472fddf1fe479e4192377752aa2/general");

	        conn = (HttpURLConnection) url.openConnection();
	        //Request 형식 설정
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	        conn.setRequestProperty("X-OCR-SECRET", "T2xnelBZekR6YWFna3pnc0tXbnhScWRvRVVwd1BlVGo=");
	        
	        //request에 JSON data 준비
	        conn.setDoOutput(true);
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	        //commands라는 JSONArray를 담을 JSONObject 생성	        
        
	        byte[] imageBytes = extractBytes("D:\\Images\\1.jpg");
	        byte[] baselncodingBytes = encodingBase64(imageBytes);
	        System.out.println(new String(baselncodingBytes));
	        
	        JSONObject jsonObject = new JSONObject(); // 중괄호에 들어갈 속성 정의 { "a" : "1", "b" : "2" }
	        JSONArray jsonArray = new JSONArray(); // 대괄호 정의 [{ "a" : "1", "b" : "2" }]
	        JSONObject finalJsonObject = new JSONObject(); // 중괄호로 감싸 대괄호의 이름을 정의함 { "c" : [{  "a" : "1", "b" : "2" }] }
	        
	        jsonObject.put("name", "test");
	        jsonObject.put("format", "jpg");
	        //jsonObject.put("url", "https://partner.kccworld.net/Content/test.jpg");
	        jsonObject.put("data", new String(baselncodingBytes));
	        jsonArray.add(jsonObject);
	        
	        finalJsonObject.put("version", "v2");
	        finalJsonObject.put("requestId", "test");
	        finalJsonObject.put("timestamp", "0");
	        finalJsonObject.put("images", jsonArray);
	        
	        System.out.println(finalJsonObject);
	        
	        //request에 쓰기
	        bw.write(finalJsonObject.toString());
	        bw.flush();
	        bw.close();
	        
	        //보내고 결과값 받기
	        int responseCode = conn.getResponseCode();
	        if (responseCode == 200) {
	        	System.out.println(responseCode);
	        	
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder sb = new StringBuilder();
	            String line = "";
	            while ((line = br.readLine()) != null) {
	                sb.append(line);
	            }
	            
	            System.out.println(sb.toString());
	            
	            JSONParser jsonParser1 = new JSONParser();
	            JSONObject jsonObject1 = (JSONObject) jsonParser1.parse(sb.toString());
	            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("images");
	            
	            for(int i=0; i<jsonArray1.size(); i++){
	                System.out.println("images "+ i +"번째 : " +jsonArray1.get(i));  
	                JSONObject jsonObject2 = (JSONObject) jsonArray1.get(i);
	                System.out.println("uid : " + jsonObject2.get("uid"));
	                System.out.println("name : " + jsonObject2.get("name"));
	                System.out.println("inferResult : " + jsonObject2.get("inferResult"));
	                System.out.println("message : " + jsonObject2.get("message"));
	                
	                JSONArray jsonArray2 = (JSONArray) jsonObject2.get("fields");
	                for (int j = 0; j < jsonArray2.size(); j++) {
	                	// System.out.println("fields "+ j +"번째 : " + jsonArray2.get(j));
	                	JSONObject jsonObject3 = (JSONObject) jsonArray2.get(j);
	                	System.out.println("inferText : " + jsonObject3.get("inferText"));
					}
	            }
	            
	            status = "Success";
	        }
	        else if (responseCode == 400) {
	        	System.out.println(400);
	        }
	        else if (responseCode == 401) {
	        	System.out.println(401);
	        }
	        else if (responseCode == 500) {
	        	System.out.println(500);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private static byte[] getFileBinary(String filepath) {
		File file = new File(filepath);
		byte[] data = new byte[(int) file.length()];
		try (FileInputStream stream = new FileInputStream(file)) {
			stream.read(data, 0, data.length);
		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
		return data;
	}

	public static byte[] extractBytes(String imageName) throws IOException{
		File imgPath = new File(imageName);
		FileInputStream fis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		fis = new FileInputStream(imgPath);
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = fis.read(buf)) != -1) {
			baos.write(buf, 0, len);
		}
		byte[] fileArray = baos.toByteArray();
		return fileArray;
	}
	
	public static byte[] encodingBase64(byte[] targetBytes) {
		Encoder encoder = Base64.getEncoder();
		return encoder.encode(targetBytes);
	}	
}

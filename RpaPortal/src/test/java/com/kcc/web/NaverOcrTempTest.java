package com.kcc.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import net.sf.json.JSONException;


public class NaverOcrTempTest {

	public static void main(String[] args) {
		String status = "Fail";
				
		HttpURLConnection conn = null;
	    JSONObject responseJson = null;
	    
	    try {
	        //URL 설정
	        URL url = new URL("https://8c4e1d2252484597b2955d676b38bd3b.apigw.ntruss.com/custom/v1/5237/62ef833a9b2f5073922cf9a0cbf0f6d9f3fb3b1923eab710a2c131536220595f/infer");

	        conn = (HttpURLConnection) url.openConnection();
	        //Request 형식 설정
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
	        conn.setRequestProperty("X-OCR-SECRET", "cmdkRmVUaEVRR2hFenBhdlZqWVRaTktLeXh1VXRLclQ=");
	        
	        //request에 JSON data 준비
	        conn.setDoOutput(true);
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	        //commands라는 JSONArray를 담을 JSONObject 생성
	        
	        JSONObject jsonObject = new JSONObject(); // 중괄호에 들어갈 속성 정의 { "a" : "1", "b" : "2" }
	        JSONArray jsonArray = new JSONArray(); // 대괄호 정의 [{ "a" : "1", "b" : "2" }]
	        JSONObject finalJsonObject = new JSONObject(); // 중괄호로 감싸 대괄호의 이름을 정의함 { "c" : [{  "a" : "1", "b" : "2" }] }
	        
	        jsonObject.put("name", "test");
	        jsonObject.put("format", "jpg");
	        jsonObject.put("url", "https://partner.kccworld.net/Content/test.jpg");
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
	                System.out.println("matchedTemplate : " + jsonObject2.get("matchedTemplate"));
	                
	                JSONObject jsonObject3 = (JSONObject) jsonObject2.get("matchedTemplate");
	                System.out.println("id : " + jsonObject3.get("id"));
	                System.out.println("name : " + jsonObject3.get("name"));
                	
	                JSONArray jsonArray2 = (JSONArray) jsonObject2.get("fields");
	                for (int j = 0; j < jsonArray2.size(); j++) {
	                	// System.out.println("fields "+ j +"번째 : " + jsonArray2.get(j));
	                	JSONObject jsonObject4 = (JSONObject) jsonArray2.get(j);
	                	System.out.println("inferText : " + jsonObject4.get("inferText"));
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

}

package com.kcc.config;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EntraTokenService {

  private final RestTemplate restTemplate;
  public EntraTokenService(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

  @Value("${powerautomate.tenant-id}")     private String tenantId;
  @Value("${powerautomate.client-id}")     private String clientId;
  @Value("${powerautomate.client-secret}") private String clientSecret;
  @Value("${powerautomate.audience}")      private String audience;

  public String getAccessToken() {
    // ★ 진입 즉시 핵심 값 확인(시크릿은 마스킹)
    System.out.println("[DEBUG] getAccessToken ENTER "
        + "tenantId=" + tenantId
        + ", clientId=" + clientId
        + ", audience=" + audience
        + ", secretNull?=" + (clientSecret == null));

    // 값 누락 방지(널이면 바로 어디가 비었는지 알려줌)
    if (tenantId == null || clientId == null || clientSecret == null || audience == null) {
      throw new IllegalStateException("OAuth config missing: "
          + "tenantId=" + tenantId + ", clientId=" + clientId
          + ", secretNull?=" + (clientSecret == null) + ", audience=" + audience);
    }

    String url = "https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token";
    String body = "grant_type=client_credentials"
        + "&client_id=" + enc(clientId)
        + "&client_secret=" + enc(clientSecret)
        + "&scope=" + enc(audience + "/.default");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    try {
    	  System.out.println("[DEBUG] token POST " + url);
    	  ResponseEntity<String> resp = restTemplate.exchange(
    	      url, HttpMethod.POST, new HttpEntity<>(body, headers), String.class);

    	  System.out.println("[DEBUG] token resp status=" + resp.getStatusCode());
    	  System.out.println("[DEBUG] token resp body=" + resp.getBody());

    	  if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody() == null) {
    	    throw new RuntimeException("Token error: " + resp.getStatusCode());
    	  }

    	  // access_token 파싱 (간단 파서)
    	  String json = resp.getBody();
    	  String marker = "\"access_token\":\"";
    	  int i = json.indexOf(marker);
    	  if (i < 0) throw new RuntimeException("Token missing in response: " + json);
    	  int j = json.indexOf('"', i + marker.length());
    	  return json.substring(i + marker.length(), j);

    	} catch (org.springframework.web.client.HttpClientErrorException e) {
    		System.out.println("[DEBUG] token 4xx status=" + e.getStatusCode());
    	    System.out.println("[DEBUG] token 4xx body=" + e.getResponseBodyAsString()); // 401이면 빈 경우 많음
    	    HttpHeaders hh = e.getResponseHeaders();
    	    if (hh != null) {
    	        String www = hh.getFirst("WWW-Authenticate");
    	        System.out.println("[DEBUG] WWW-Authenticate=" + www); // ← AADSTS 코드 보통 여기!
    	    }
    	    throw e;
    	} catch (Exception e) {
    	  System.out.println("[DEBUG] token exception=" + e);
    	  throw e;
    	}
  }

  // JDK8 호환
  private static String enc(String s) {
    try { return URLEncoder.encode(s, "UTF-8"); }
    catch (Exception e) { throw new RuntimeException(e); }
  }
}

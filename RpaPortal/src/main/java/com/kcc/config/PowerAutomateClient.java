package com.kcc.config;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.util.UriTemplateHandler;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class PowerAutomateClient {

  @Value("${powerautomate.flow-url}")
  private String flowUrl;

  private final RestTemplate rawRest;
  private final EntraTokenService tokenService; // SAS만 쓸 거면 제거해도 됨

  public PowerAutomateClient(EntraTokenService tokenService) {
    this.rawRest = new RestTemplate();
    this.tokenService = tokenService;

    // URL 재인코딩 방지 (sp=%2F → %252F로 변조되는 이슈 해결)
    this.rawRest.setUriTemplateHandler(new NoEncodingUriTemplateHandler());
    this.rawRest.getInterceptors().add((req, body, ex) -> {
      System.out.println("=== [REQ] " + req.getMethod() + " " + req.getURI());
      System.out.println("=== [REQ] Authorization=" + req.getHeaders().getFirst("Authorization"));
      System.out.println("=== [REQ] Content-Type=" + req.getHeaders().getFirst("Content-Type"));
      return ex.execute(req, body);
    });
  }

  private static class NoEncodingUriTemplateHandler implements UriTemplateHandler {
    @Override public URI expand(String u, Object... v){ return URI.create(u); }
    @Override public URI expand(String u, Map<String, ?> v){ return URI.create(u); }
  }

  /** 외부에서 쓰는 통합 메서드 */
  public Map<String,Object> invokeFlow(Map<String,Object> payload) {
    if (isSasUrl(flowUrl)) {
      return invokeSas(flowUrl, payload);
    } else {
      return invokeOAuth(flowUrl, payload);
    }
  }

  private boolean isSasUrl(String url) {
    return url != null && url.contains("sig=");
  }

  /** SAS 전용 호출: Authorization 금지, JSON이면 Content-Type만 */
  private Map<String,Object> invokeSas(String url, Map<String,Object> payload) {
    HttpHeaders h = new HttpHeaders();
    if (payload != null && !payload.isEmpty()) {
      h.setContentType(MediaType.APPLICATION_JSON);
    }
    HttpEntity<?> entity = (payload == null || payload.isEmpty())
        ? new HttpEntity<>(null, h)
        : new HttpEntity<>(payload, h);

    ResponseEntity<Map> resp = rawRest.exchange(url, HttpMethod.POST, entity, Map.class);
    if (!(resp.getStatusCode().is2xxSuccessful()
        || resp.getStatusCodeValue()==201
        || resp.getStatusCodeValue()==202)) {
      throw new RuntimeException("Flow error(SAS): " + resp.getStatusCode() + " " + resp.getBody());
    }
    @SuppressWarnings("unchecked")
    Map<String,Object> body = (Map<String,Object>) resp.getBody();
    return body != null ? body : new HashMap<>();
  }

  /** OAuth 전용 호출: Bearer 붙임 (audience/.default로 발급된 토큰이어야 함) */
  private Map<String,Object> invokeOAuth(String url, Map<String,Object> payload) {
    String token = tokenService.getAccessToken(); // 이 토큰의 aud가 Flow가 요구하는 GUID인지 확인 필요
    JwtDebug.printClaims(token);

    HttpHeaders h = new HttpHeaders();
    h.setContentType(MediaType.APPLICATION_JSON);
    h.setBearerAuth(token);

    ResponseEntity<Map> resp = rawRest.exchange(url, HttpMethod.POST, new HttpEntity<>(payload, h), Map.class);
    if (!resp.getStatusCode().is2xxSuccessful()) {
      throw new RuntimeException("Flow error(OAuth): " + resp.getStatusCode() + " " + resp.getBody());
    }
    @SuppressWarnings("unchecked")
    Map<String,Object> body = (Map<String,Object>) resp.getBody();
    return body != null ? body : new HashMap<>();
  }

  /** 디버그용 */
  public static class JwtDebug {
    public static void printClaims(String jwt) {
      try {
        String[] p = jwt.split("\\.");
        String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(p[1]),
                                        java.nio.charset.StandardCharsets.UTF_8);
        System.out.println("[JWT] " + payloadJson);
      } catch (Exception e) {
        System.out.println("[JWT] decode error: " + e);
      }
    }
  }
}

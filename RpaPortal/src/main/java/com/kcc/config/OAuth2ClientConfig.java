package com.kcc.config;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.Collections;

@Configuration
public class OAuth2ClientConfig {

  @Value("${powerautomate.tenant-id}")  private String tenantId;
  @Value("${powerautomate.client-id}")  private String clientId;
  @Value("${powerautomate.client-secret}") private String clientSecret;
  @Value("${powerautomate.audience}")   private String audience; // GUID

  @Bean
  public ClientCredentialsResourceDetails clientCreds() {
    ClientCredentialsResourceDetails d = new ClientCredentialsResourceDetails();
    d.setClientId(clientId);
    d.setClientSecret(clientSecret);
    d.setAccessTokenUri("https://login.microsoftonline.com/" + tenantId + "/oauth2/v2.0/token");
    d.setScope(Collections.singletonList(audience + "/.default"));
    d.setClientAuthenticationScheme(AuthenticationScheme.form);
    return d;
  }

  @Bean
  public OAuth2RestTemplate oAuth2RestTemplate(ClientCredentialsResourceDetails details) {
    OAuth2RestTemplate t = new OAuth2RestTemplate(details);

    HttpComponentsClientHttpRequestFactory f = new HttpComponentsClientHttpRequestFactory();
    f.setConnectTimeout(5000);
    f.setReadTimeout(15000);
    t.setRequestFactory(f);

    // ★ 여기 인터셉터 추가 (Authorization 헤더 확인용)
    t.getInterceptors().add((req, body, ex) -> {
      System.out.println("[HTTP] " + req.getMethod() + " " + req.getURI());
      String auth = req.getHeaders().getFirst("Authorization");
      System.out.println("[HTTP] Authorization present? " + (auth != null && auth.startsWith("Bearer ")));
      return ex.execute(req, body);
    });

    return t;
  }
}

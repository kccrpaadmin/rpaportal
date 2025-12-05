package com.kcc.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@ComponentScan(
  basePackages = "com.kcc.config",
  excludeFilters = {
    @ComponentScan.Filter(org.springframework.stereotype.Controller.class),
    @ComponentScan.Filter(org.springframework.web.bind.annotation.RestController.class)
  }
)
public class AppConfig {

  @Bean
  public RestTemplate restTemplate(
	  @Value("${http.connectTimeout:5000}") int connectTimeout,
	  @Value("${http.readTimeout:15000}") int readTimeout) {
	  SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
	  f.setConnectTimeout(connectTimeout);
	  f.setReadTimeout(readTimeout);
//test
	  RestTemplate rt = new RestTemplate(f);

	  // ★ 요청 로그 (Authorization 헤더 포함 여부 확인)
	  rt.getInterceptors().add((req, body, ex) -> {
	    System.out.println("[HTTP] " + req.getMethod() + " " + req.getURI());
	    String auth = req.getHeaders().getFirst("Authorization");
	    System.out.println("[HTTP] Authorization present? " + (auth != null && auth.startsWith("Bearer ")));
	    return ex.execute(req, body);
	  });

	  return rt;
  }
}
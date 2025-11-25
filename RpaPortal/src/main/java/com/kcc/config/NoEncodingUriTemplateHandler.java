package com.kcc.config;

import org.springframework.web.util.UriTemplateHandler;
import java.net.URI;

public class NoEncodingUriTemplateHandler implements UriTemplateHandler {
    @Override
    public URI expand(String uriTemplate, Object... uriVariables) {
        return URI.create(uriTemplate);
    }
    @Override
    public URI expand(String uriTemplate, java.util.Map<String, ?> uriVariables) {
        return URI.create(uriTemplate);
    }
}

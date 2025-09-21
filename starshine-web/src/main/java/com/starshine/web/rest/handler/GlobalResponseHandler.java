package com.starshine.web.rest.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starshine.common.web.RestfulResult;
import com.starshine.shared.annotation.IgnoreResponseAdvice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    private final ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果已经是 RestfulResult，不再包装
        return !RestfulResult.class.isAssignableFrom(returnType.getParameterType()) &&
                !returnType.getContainingClass().isAnnotationPresent(IgnoreResponseAdvice.class) &&
                !returnType.hasMethodAnnotation(IgnoreResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        if (body instanceof String) {
            try {
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                return objectMapper.writeValueAsString(RestfulResult.success(body));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return RestfulResult.success(body);
    }
}
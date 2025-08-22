package com.festival.everyday.core.common.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Set-Cookie") // 클라이언트에서 접근해야 하는 헤더 노출
                .allowCredentials(true) // 쿠키/인증정보 포함 요청 허용 시 필요
                .maxAge(3600); // preflight 캐시 (1시간)
    }
}

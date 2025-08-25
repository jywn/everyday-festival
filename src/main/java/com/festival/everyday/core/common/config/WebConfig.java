package com.festival.everyday.core.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173",
                        "http://localhost:5178",
                        "https://festival-everyday.vercel.app",
                        "https://everyday-festival-1.vercel.app") // React 개발 서버 주소
                .allowedOriginPatterns("https://everyday-festival-*.vercel.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Set-Cookie") // 클라이언트에서 접근해야 하는 헤더 노출
                .allowCredentials(true) // 쿠키/인증정보 포함 요청 허용 시 필요
                .maxAge(3600); // preflight 캐시 (1시간)
    }
}

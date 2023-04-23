package com.eastshine.rapcrewapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // 적용할 URL 패턴을 지정.
                .allowedOrigins("http://localhost:8082") // 허용할 오리진을 지정.
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드를 지정.
                .maxAge(3600); // 캐시 유효 시간을 지정.
    }
}

package com.goree.api.config;

import com.goree.api.auth.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig {
    @Autowired
    private AuthInterceptor authInterceptor;

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {

        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(authInterceptor)
                .excludePathPatterns("/member/join");
            }
        };
    }
}

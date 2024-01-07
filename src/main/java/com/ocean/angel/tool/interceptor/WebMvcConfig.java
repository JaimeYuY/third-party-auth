package com.ocean.angel.tool.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 定义拦截器拦截规则：拦截除令牌获取/openapi/token/get接口外的其他的所有/openapi接口
        registry.addInterceptor(new OpenApiAuthInterceptor())
                .addPathPatterns("/openapi/**")
                .excludePathPatterns("/openapi/token/get");
    }
}

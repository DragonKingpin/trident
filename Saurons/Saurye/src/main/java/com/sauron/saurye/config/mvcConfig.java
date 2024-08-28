package com.sauron.saurye.config;

import com.sauron.saurye.Interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class mvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private UserInterceptor userInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("");
    }
}

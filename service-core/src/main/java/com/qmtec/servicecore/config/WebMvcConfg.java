package com.qmtec.servicecore.config;

import com.qmtec.servicecore.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    public static final String SESSION_KEY="userId";

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(loginInterceptor);
        //拦截配置
        addInterceptor.addPathPatterns("/**/**");

        //排除配置
        addInterceptor.excludePathPatterns("/");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/loginVerify");
        addInterceptor.excludePathPatterns("/api/**");
        addInterceptor.excludePathPatterns("/css/**");
        addInterceptor.excludePathPatterns("/images/**");
        addInterceptor.excludePathPatterns("/js/**");
        addInterceptor.excludePathPatterns("/lib/**");
        addInterceptor.excludePathPatterns("/flumeConfig/**");
        addInterceptor.excludePathPatterns("/DataXConfig/**");
    }
}

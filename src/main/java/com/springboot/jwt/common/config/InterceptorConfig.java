package com.springboot.jwt.common.config;

import com.springboot.jwt.common.intercepter.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
/*    private TokenInterceptor tokenInterceptor;
    //构造方法
    public InterceptorConfig(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor =tokenInterceptor;
    }*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/login");//登录
        excludePath.add("/register");//注册
        excludePath.add("/logout");//登出
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源
    registry
        .addInterceptor(new TokenInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns(excludePath);
   // WebMvcConfigurer.super.addInterceptors(registry);
    }
}

package org.chenzhiqiang;

import org.chenzhiqiang.authority.interceptor.AuthorityInterceptor;
import org.chenzhiqiang.login.interceptor.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan({"org.chenzhiqiang.mapper", "org.chenzhiqiang.authority.mapper",
        "org.chenzhiqiang.OperateMenu.mapper", "org.chenzhiqiang.login.mapper"})
@ServletComponentScan("org.chenzhiqiang.authority.listener")
//@ServletComponentScan(basePackages = "org.chenzhiqiang.authority.linstener")
//public class DepartmentAppStart implements WebMvcConfigurer {
public class DepartmentAppStart{
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    @Autowired
//    private AuthorityInterceptor authorityInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login","/logout","/OperateMenu/tree/");
//        registry.addInterceptor(authorityInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login","/logout","/OperateMenu/tree/");
//    }

    public static void main(String[] args) {
        SpringApplication.run(DepartmentAppStart.class,args);
    }
}


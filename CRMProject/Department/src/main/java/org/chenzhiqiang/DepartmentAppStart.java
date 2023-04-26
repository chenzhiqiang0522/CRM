package org.chenzhiqiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan({"org.chenzhiqiang.mapper", "org.chenzhiqiang.authority.mapper"})
@ServletComponentScan("org.chenzhiqiang.authority.linstener")
public class DepartmentAppStart {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentAppStart.class,args);
    }
}

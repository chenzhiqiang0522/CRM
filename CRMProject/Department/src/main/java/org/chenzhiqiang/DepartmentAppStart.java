package org.chenzhiqiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.chenzhiqiang.mapper")
public class DepartmentAppStart {
    public static void main(String[] args) {
        SpringApplication.run(DepartmentAppStart.class,args);
    }
}

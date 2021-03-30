package com.flycinema;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan(basePackages = "com.flycinema.flycinema_boot.dao")
public class FlycinemaBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlycinemaBootApplication.class, args);
    }

}

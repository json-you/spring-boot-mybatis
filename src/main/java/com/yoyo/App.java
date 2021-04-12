package com.yoyo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fotic")//指定要变成实现类的接口所在的包
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

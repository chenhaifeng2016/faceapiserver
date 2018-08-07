package com.mycompany.faceapiserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
//@ComponentScan({"com.mycompany.faceapiserver.group.service"})
@MapperScan({"com.mycompany.faceapiserver.group.dao.*", "com.mycompany.faceapiserver.user.dao.*"})
public class FaceapiserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceapiserverApplication.class, args);
    }
}

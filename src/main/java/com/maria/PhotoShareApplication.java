package com.maria;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by  on 8/8/2017.
 */
@Configuration
@ComponentScan(basePackages = {"com.maria"})
@SpringBootApplication
@EnableAutoConfiguration
public class PhotoShareApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PhotoShareApplication.class, args);
    }
}

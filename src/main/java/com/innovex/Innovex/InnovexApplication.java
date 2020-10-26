package com.innovex.Innovex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InnovexApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnovexApplication.class, args);
    }
}

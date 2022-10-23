package com.giggle.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// (scanBasePackages={"com.giggle.api"})
public class InitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitApiApplication.class, args);
    }

}

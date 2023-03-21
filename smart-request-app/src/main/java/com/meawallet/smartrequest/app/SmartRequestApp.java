package com.meawallet.smartrequest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.meawallet")
public class SmartRequestApp {
    public static void main(String[] args) {
        SpringApplication.run(SmartRequestApp.class, args);
    }
}

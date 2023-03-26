package com.meawallet.smartrequest.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Clock;

@Configuration
@EnableJpaRepositories(basePackages = "com.meawallet")
@EntityScan(basePackages = "com.meawallet")
public class DataBaseConfig {
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
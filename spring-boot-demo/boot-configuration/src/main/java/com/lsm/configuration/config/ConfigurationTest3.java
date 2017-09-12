package com.lsm.configuration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.properties")
@PropertySource("classpath:test.properties")
@Data
public class ConfigurationTest3 {

    private String name;

    private String age;

    private String city;
}

package com.lsm.configuration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.lsm")
@Data
public class ConfigurationTest2 {

    private String name;

    private String age;

    private String city;

}

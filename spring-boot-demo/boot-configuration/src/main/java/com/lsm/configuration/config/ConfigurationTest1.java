package com.lsm.configuration.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConfigurationTest1 {

    @Value("${com.test.name}")
    private String name;
    @Value("${com.test.age}")
    private String age;
    @Value("${com.test.city}")
    private String city;
}

package com.lsm.configuration.service;

import com.lsm.configuration.config.ConfigurationTest1;
import com.lsm.configuration.config.ConfigurationTest2;
import com.lsm.configuration.config.ConfigurationTest3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 4种读取配置的方式
 */
@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationTest1 configurationTest1;
    @Autowired
    private ConfigurationTest2 configurationTest2;
    @Autowired
    private ConfigurationTest3 configurationTest3;
    @Autowired
    private Environment env;

    public void configurationTest1() {
        System.out.println(configurationTest1.toString());
    }

    public void configurationTest2() {
        System.out.println(configurationTest2.toString());
    }

    public void configurationTest3() {
        System.out.println(configurationTest3.toString());
    }

    public void configurationTest4() {
        System.out.println("com.application.environment = " + env.getProperty("com.application.environment")
                + ", com.properties.environment = "+ env.getProperty("com.properties.environment"));
    }
}

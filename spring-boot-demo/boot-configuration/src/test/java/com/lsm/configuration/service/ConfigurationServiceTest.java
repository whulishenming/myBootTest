package com.lsm.configuration.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationServiceTest {
    @Autowired
    private ConfigurationService configurationService;

    @Test
    public void configurationTest1() throws Exception {
        configurationService.configurationTest1();
    }

    @Test
    public void configurationTest2() throws Exception {
        configurationService.configurationTest2();
    }

    @Test
    public void configurationTest3() throws Exception {
        configurationService.configurationTest3();
    }

    @Test
    public void configurationTest4() throws Exception {
        configurationService.configurationTest4();
    }

}
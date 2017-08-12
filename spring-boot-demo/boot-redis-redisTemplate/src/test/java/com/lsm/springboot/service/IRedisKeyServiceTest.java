package com.lsm.springboot.service;

import com.lsm.springboot.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class IRedisKeyServiceTest extends BaseTest {

    @Autowired
    private IRedisKeyService redisKeyServiceImpl;
    @Autowired
    private IRedisListService redisListServiceImpl;
    @Test
    public void del() throws Exception {
    }

    @Test
    public void exists() throws Exception {
    }

    @Test
    public void expire() throws Exception {
    }

    @Test
    public void expireAt() throws Exception {
    }

    @Test
    public void ttl() throws Exception {
    }

    @Test
    public void randomKey() throws Exception {
    }

    @Test
    public void type() throws Exception {
    }

}
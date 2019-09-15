package com.mr.web.controller;


import com.mr.pojo.OrderPaidEvent;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by stack on 2019/6/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BRoleControllerTest {

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {

//        for (int i = 0; i < 100; i++) {
//            stringRedisTemplate.opsForValue().set("token:" + UUID.randomUUID().toString(), UUID.randomUUID().toString());
//        }

        System.out.println(1);

    }

}
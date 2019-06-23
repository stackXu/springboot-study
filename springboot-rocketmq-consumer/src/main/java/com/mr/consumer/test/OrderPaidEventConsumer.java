package com.mr.consumer.test;

import com.alibaba.fastjson.JSON;
import com.mr.pojo.OrderPaidEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

// 声明消费消息的类，并在注解中指定，相关的消费信息
@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class OrderPaidEventConsumer implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent message) {

        System.out.print("------- OrderPaidEventConsumer received:"+ JSON.toJSONString(message));
    }

}
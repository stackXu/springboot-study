package com.mr.web.controller;

import com.mr.common.ResponseObject;
import com.mr.pojo.OrderPaidEvent;
import com.mr.web.annotation.MyPermission;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName RocketMqTestController
 * @Description rocketMq测试
 * @Author stack
 * @Version 1.0
 * @since 2019/6/23 17:03
 */
@RestController
@Slf4j
public class RocketMqTestController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping(value = "/testRocketMq/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "测试rocketMq")
    public ResponseObject testDubbo(@PathVariable("name") String name, HttpServletRequest request, Model model) {
        rocketMQTemplate.convertAndSend("test-topic-1",name);
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload(new OrderPaidEvent("aa,22",new BigDecimal("22"))).build());
        ResponseObject<Object> responseObject = new ResponseObject<>();
        responseObject.success(1);
        return responseObject;
    }


}

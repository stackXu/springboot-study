package com.mr.web.controller;

import com.mr.common.ResponseObject;
import com.mr.web.annotation.MyLimit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MyLimitTestController
 * @Description MyLimitTestController
 * @Author stack
 * @Version 1.0
 * @since 2019/7/15 17:06
 */
@RestController
@Slf4j
@Api(tags = "MyLimitTestController", description = "限流注解测试controller")
@RequestMapping(value = "/MyLimitTestController")
public class MyLimitTestController {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @MyLimit(permitsPerSecond = 1)
    @ApiOperation(value = "测试重载getUserId")
    public ResponseObject test1(HttpServletRequest request) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.success("ok");
        return responseObject;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @MyLimit()
    @ApiOperation(value = "测试重载getUserId")
    public ResponseObject test2(HttpServletRequest request) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.success("ok");
        return responseObject;
    }

}

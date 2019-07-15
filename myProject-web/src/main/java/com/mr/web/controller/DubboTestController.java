package com.mr.web.controller;

import com.mr.common.ResponseObject;
import com.mr.web.annotation.MyPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by stack on 2019/6/19.
 */
@RestController
@Slf4j
@MyPermission(needRole = MyPermission.Role.A )
public class DubboTestController {

//    @Reference(loadbalance="roundrobin", retries=2,cluster="failsafe")
//     private TestDubboService testDubboService;
//
//    @RequestMapping(value = "/testdubbo/{name}", method = RequestMethod.GET)
//    @ApiOperation(value = "测试dubbo")
//    public ResponseObject testDubbo(@PathVariable("name") String name, HttpServletRequest request, Model model) {
//        ResponseObject responseObject = new ResponseObject();
//        String s = testDubboService.testDubbo(name);
//        responseObject.success(s);
//        return responseObject;
//    }

}

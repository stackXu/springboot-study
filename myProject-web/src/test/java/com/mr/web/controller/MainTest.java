package com.mr.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mr.pojo.User;

/**
 * @ClassName MainTest
 * @Author stack
 * @Version 1.0
 * @since 2019/6/23 20:10
 */
public class MainTest {

    public static void main(String[] args) throws Exception{
        String a="{\"name\":\"ddddddddd\"}";
        ObjectMapper objectMapper =new  ObjectMapper();
        User user = objectMapper.readValue(a, User.class);
        System.out.println(user.getName());
    }

}

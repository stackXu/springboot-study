package com.mr.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.Instant;

/**
 * 启动程序
 */
@SpringBootApplication
public class DubboPrividerApplication {

    private static final Logger log = LoggerFactory.getLogger(DubboPrividerApplication.class);

    public static void main(String[] args) {
        Instant inst1 = Instant.now();
        SpringApplication.run(DubboPrividerApplication.class, args);
        log.info("基于 Spring Boot {} ", SpringBootVersion.getVersion());
        log.info("启动成功!耗时:{}秒 ", Duration.between(inst1, Instant.now()).getSeconds());
    }

}
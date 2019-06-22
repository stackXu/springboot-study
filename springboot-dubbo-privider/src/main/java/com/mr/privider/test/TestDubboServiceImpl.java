package com.mr.privider.test;

import com.mr.interfaces.test.TestDubboService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @ClassName TestDubboServiceImpl
 * @Description dubbo测试接口的实现类
 * @Author stack
 * @Version 1.0
 * @since 2019/6/21 10:20
 */
@Component
@Service(loadbalance = "roundrobin")
@Slf4j
public class TestDubboServiceImpl implements TestDubboService {

    @Value("${spring.profiles.active}")
    private String cusvar;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String testDubbo(String s) {
        String testDubbo = stringRedisTemplate.opsForValue().get("testDubbo");
        if ("error".equals(testDubbo)) {
            log.error("错误....");
            throw new NullPointerException();
        }
        log.error("成功....");
        return cusvar + "dubbo测试接口的实现类" + s;
    }


}

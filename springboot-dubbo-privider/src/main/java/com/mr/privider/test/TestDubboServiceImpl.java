package com.mr.privider.test;

import com.mr.interfaces.test.TestDubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;



/**
 * @ClassName TestDubboServiceImpl
 * @Description dubbo测试接口的实现类
 * @Author stack
 * @Version 1.0
 * @since 2019/6/21 10:20
 */
@Component
@Service
public class TestDubboServiceImpl implements TestDubboService {

    @Override
    public String testDubbo(String s) {
        return "dubbo测试接口的实现类"+s;
    }


}

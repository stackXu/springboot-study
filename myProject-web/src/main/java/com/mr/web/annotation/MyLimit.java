package com.mr.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyLimit
 * @Description MyLimit
 * @Author stack
 * @Version 1.0
 * @since 2019/7/15 16:47
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLimit {

    /**
     * 每秒向桶中放入令牌的数量
     * 默认最大即不做限流,使用简单的RateLimit,每一秒中放入多少令牌
     * @return
     */
    int permitsPerSecond() default Integer.MAX_VALUE;

    /**
     * 获取令牌的等待时间  默认0
     * @return
     */
    int timeOut() default 0;

    /**
     * 超时时间单位
     * @return
     */
    TimeUnit unit() default TimeUnit.SECONDS;

}

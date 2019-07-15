package com.mr.web.annotation;

import com.google.common.util.concurrent.RateLimiter;
import com.mr.common.ResultCode;
import com.mr.exception.MyprojectException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MyLimit
 * @Description MyLimit
 * @Author stack
 * @Version 1.0
 * @since 2019/7/15 16:47
 */

@Aspect
@Component
@Slf4j
public class MyLimitAspect {

    private static Map<String, RateLimiter> url = new ConcurrentHashMap<>();

    private static RateLimiter rateLimiter = RateLimiter.create(1000);


    @Pointcut("@annotation(com.mr.web.annotation.MyLimit)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        MyLimit methodAnnotation = method.getAnnotation(MyLimit.class);

        double v = methodAnnotation.permitsPerSecond();
        if (v == Integer.MAX_VALUE) {
            return;
        }

        if (methodAnnotation != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String requestURL = request.getRequestURL().toString();
            //根据url判断是否限流。
            if (url.containsKey(requestURL)) {
                RateLimiter rateLimiter = url.get(requestURL);
                if (!rateLimiter.tryAcquire(methodAnnotation.timeOut(), methodAnnotation.unit())) {
                    //限流
                    throw new MyprojectException(ResultCode.SYSTEM_ERROR.getError());
                }
            } else {
//                RateLimiter r = RateLimiter.create(v);
                rateLimiter = RateLimiter.create(v);
                url.put(requestURL, rateLimiter);
//                url.put(requestURL, r);
            }
        }
    }

}

package com.mr.web.interceptor;

import com.mr.common.ResponseObject;
import com.mr.exception.MyprojectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@EnableWebMvc
@ControllerAdvice
public class GlobalHandlerExceptionResolver {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 数据找不到异常
     *
     * @param ex
     * @param request
     * @return
     * @throws IOException
     */
    @ExceptionHandler({MyprojectException.class})
    @ResponseBody
    public ResponseObject handleDataNotFoundException(MyprojectException ex, WebRequest request) throws IOException {
        ResponseObject responseObject = new ResponseObject();
        responseObject.failed(ex.getMsg());
        return responseObject;
    }

}

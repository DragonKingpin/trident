package com.sauron.saurye.handler;

import com.sauron.saurye.exception.BaseException;
import com.sauron.saurye.result.BasicServiceResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public BasicServiceResponse exceptionHandler(BaseException ex){
        return BasicServiceResponse.error(ex.getMessage());
    }


}

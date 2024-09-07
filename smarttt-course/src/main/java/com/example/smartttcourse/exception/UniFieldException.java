package com.example.smartttcourse.exception;

import com.example.smartttcourse.exception.cus.BusinessException;
import com.example.smartttcourse.exception.res.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/** 全局异常处理器
 * @author lunSir
 * @create 2024-09-07 14:09
 */
@RestControllerAdvice
@Slf4j
public class UniFieldException {
    /**
     * 业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result exceptionHandle(BusinessException e){
        log.error(e.getMessage());

        return Result.error().msg(e.getMessage()).code(e.getCode());
    }


//    /**
//     * 服务器内部出现异常
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = Exception.class)
//    public Result exceptionHandle(Exception e){
//        log.error(e.getMessage());
//        return Result.error().msg("服务繁忙，请稍后重试！");
//    }

}

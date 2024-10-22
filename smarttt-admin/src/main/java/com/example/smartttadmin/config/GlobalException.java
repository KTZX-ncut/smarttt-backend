package com.example.smartttadmin.config;

import com.example.smartttadmin.dto.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lunSir
 * @create 2024-10-21 20:11
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(RuntimeException.class)
    public Result exceptionHandler(Exception e){
        return Result.error(-710,e.getMessage());
    }
}

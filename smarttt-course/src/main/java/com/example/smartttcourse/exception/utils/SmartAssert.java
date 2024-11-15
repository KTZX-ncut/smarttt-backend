package com.example.smartttcourse.exception.utils;

import com.example.smartttcourse.exception.cus.BusinessException;
import com.example.smartttcourse.exception.res.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author lunSir
 * @create 2024-09-07 14:02
 */
@Slf4j
public abstract class SmartAssert {
    /**
     * 断言对象不为空
     * 如果对象obj为空，则抛出异常
     * @param obj 待判断对象
     */
    public static void notEmpty(String obj, ResponseEnum responseEnum) {
        if (obj == null || "".equals(obj)) {
            throw new BusinessException(responseEnum);
        }
    }



    public static void notFalse(boolean b, ResponseEnum responseEnum) {
        if(!b) throw new BusinessException(responseEnum);
    }

    public static void notNull(Object o, ResponseEnum responseEnum) {
        if(o == null) throw new BusinessException(responseEnum);
    }

    public static void eq(String usernameInDataSource, String username, ResponseEnum responseEnum) {
        if(!usernameInDataSource.equals(username)) throw new BusinessException(responseEnum);
    }

    public static void checkExpression(boolean flag,ResponseEnum responseEnum){
        if (!flag) throw new BusinessException(responseEnum);
    }
}

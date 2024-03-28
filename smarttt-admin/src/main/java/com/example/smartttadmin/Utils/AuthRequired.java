package com.example.smartttadmin.Utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthRequired {
    String type() default "error";
    String menu() default "error";
    //标记这个接口是否是只展示数据，不是编辑的
    //默认是不是只读的
    boolean isReadOnly() default false;
}
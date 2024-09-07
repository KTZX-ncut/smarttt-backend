package com.example.smartttcourse.exception.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author lunSir
 * @create 2024-09-07 14:10
 */
@Getter
@AllArgsConstructor
@ToString
public enum ResponseEnum {
    SUCCESS(0, "成功"),
    ERROR(-1, "服务器内部错误"),
    SERVLET_ERROR(-2,"controller上层错误"),
    PARAM_IS_NOT_NULL(-710,"参数为空"),
    INSERT_FAIL(-711,"插入失败")
    ;
    private Integer code;//状态码
    private String message;//消息
}

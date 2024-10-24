package com.example.smartttadmin.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * 判断是教师还是学生的枚举类
 * @author lunSir
 * @create 2024-10-18 12:00
 */
@Getter
public enum CateLogEnum {
    STUDENT(1,"学生"),
    TEACHER(2,"教师");
    private Integer status;
    private String identity;

    CateLogEnum(Integer status,String identity){
        this.status = status;
        this.identity = identity;
    }

    public static CateLogEnum getCateLogEnumByStatus(Integer status){
        for (CateLogEnum cateLogEnum : CateLogEnum.values()) {
            if (Objects.equals(cateLogEnum.getStatus(),status)){
                return cateLogEnum;
            }
        }
        throw new RuntimeException("status不合法，非正常请求！");
    }

    public static CateLogEnum getCateLogEnumByIdentity(String identity){
        for (CateLogEnum cateLogEnum : CateLogEnum.values()) {
            if (Objects.equals(cateLogEnum.getIdentity(),identity)){
                return cateLogEnum;
            }
        }
        throw new RuntimeException("status不合法，非正常请求！");
    }
}

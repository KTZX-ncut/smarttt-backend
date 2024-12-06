package com.example.smartttevaluation.exception.res;

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
    INSERT_FAIL(-711,"插入失败"),
    TOKEN_IS_NULL(-712, "token为空"),
    FIELD_IS_NULL(-713, "所要求的字段为空，请检查字段！"),
    USERNAME_NOT_EQ(-714, "有错误信息：学号和姓名不对应"),
    DATA_NOT_VALIDATE(-715,"数据不合法"),
    TERM_ID_IS_NOT_BLANK(-716,"schooltermId不能为空"),
    CLASSROOM_ID_NOT_NULL(-710,"课堂id不能为空"),
    USER_ID_NOT_NULL(-710,"用户id不能为空"),
    PRONAME_NOT_NULL(-710,"专业名称不能为空"),
    OBSNAME_NOT_NULL(-710,"班级名称不能为空"),
    OBSID_NOT_NULL(-710,"obsid不能为空"),
    LOGIN_NAME_NOT_NULL(-710,"登录名称不能为空"),
    USERNAME_NOT_NULL(-710,"username不能为空"),
    WEEK_LE_ZERO_FAIL(-710,"教学周必须大于等于0"),
    WEEK_FAIL(-710,"教学周不能为空"),
    COURSE_ID_NOT_NULL(-710,"课程id不能为空"),
    STUID_IS_NOT_NULL(-710,"学生的userId不能为空"),
    TEST_ID_NOT_NULL(-710,"考试id(testId)不能为空"),
    TEST_ID_NOT_VALID(-710,"考试id(testId) 不合法！"),
    PAPER_ID_NOT_NULL(-710,"试卷id(paperId) 不能为空!");


    private Integer code;//状态码
    private String message;//消息
}

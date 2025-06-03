package com.example.smartttevaluation.Utils;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DynamicTable {
    /**
     * 要替换的基础表名(不需要后缀)
     * @return 默认替换cm_term表
     */
    String value() default "ai_in_stu_ans_info";

    /**
     * 是否强制替换(即使表名已包含后缀)
     * @return 默认false
     */
    boolean force() default false;

    /**
     * 是否替换为当前学期
     * @return 默认true
     */
    boolean isCurrent() default true;
}

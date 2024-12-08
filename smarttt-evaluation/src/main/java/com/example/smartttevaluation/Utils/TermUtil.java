package com.example.smartttevaluation.Utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lunSir
 * @create 2024-11-28 21:02
 */
@Slf4j
public class TermUtil {
    /**
     * 获取教学周
     */
    public static Long getTeachingWeek(String startTime,String nowTime){
        // 学期开始日期
        DateTime semesterStart = DateUtil.parseDate(startTime);
        // 当前日期
        DateTime now = DateUtil.parseDate(nowTime);
        // 计算当前日期与学期开始日期之间的天数差
        Long daysBetween = DateUtil.betweenDay(semesterStart, now, false);
        log.info("daysBetween: " + daysBetween);
        // 计算教学周数，学期开始那天算作第一周
        return daysBetween / 7 + 1;
    }
}

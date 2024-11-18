package com.example.smartttcourse.enums;

import lombok.Getter;

/**
 * 课堂文件管理枚举类
 * @author lunSir
 * @create 2024-10-30 20:05
 */
@Getter
public enum CourseFileManageEnum {
    LESSON_PLAN("LessonPlan","课程教案"),
    COURSE_RESOURCES("courseresources","课程资源"),
    TEACHING_PROGRAM("teachingprogram","教学大纲"),
    ACADEMIC_CALENDAR("academiccalendar","教学日历");
    private String fileName;
    private String desc;
    CourseFileManageEnum(String fileName,String desc){
        this.fileName = fileName;
        this.desc = desc;
    }
}

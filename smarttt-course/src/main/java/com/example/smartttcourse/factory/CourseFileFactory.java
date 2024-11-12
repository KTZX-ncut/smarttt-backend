package com.example.smartttcourse.factory;

import com.example.smartttcourse.enums.CourseFileManageEnum;
import com.example.smartttcourse.factory.handler.CourseFileHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理课程文件模块的上传和下载
 * @author lunSir
 * @create 2024-10-30 23:55
 */
@Component
public class CourseFileFactory implements InitializingBean {
    @Resource
    private List<CourseFileHandler> courseFileHandlerList;
    private Map<CourseFileManageEnum,CourseFileHandler> map = new HashMap<>();

    public CourseFileHandler getHandler(CourseFileManageEnum type){
        return map.get(type);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (CourseFileHandler handler : courseFileHandlerList) {
            map.put(handler.isSupport(),handler);
        }
    }
}

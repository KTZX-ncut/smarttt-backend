package com.example.smartttexam.mapper;
import com.example.smartttexam.schedule.entity.EduMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GlobalMsgWriteMapper {

    /** 插入一条待处理消息 */
    int insert(EduMessage message);
}
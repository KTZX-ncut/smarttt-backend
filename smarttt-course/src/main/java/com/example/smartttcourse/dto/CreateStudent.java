package com.example.smartttcourse.dto;

import com.google.common.base.Preconditions;
import lombok.Data;

/**
 * @author lunSir
 * @create 2024-09-08 23:02
 */
@Data
public class CreateStudent {
    private String classRoomId;
    private String usersid;
    private String username;
    private String obsname;
    private String proname;
    private String loginname;
    private String obsid; // 班级id
    private long rowNo;
    private double courseScore;
}

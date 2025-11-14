package com.example.smartttevaluation.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IdeologyValue{
    private String id;
    private String vname;
    private String remark;
    private String courseId;
    private String parentId;
    private Integer leaf;
    private Integer level;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

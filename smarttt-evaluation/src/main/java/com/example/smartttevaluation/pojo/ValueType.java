package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ValueType {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String remark;
    private LocalDateTime createTime;
}

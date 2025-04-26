package com.example.smartttevaluation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ValueTag {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String remark;
    private LocalDateTime createTime;

    @TableField("type_id")
    private Long typeId;
}

package com.example.smartttcourse.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CmCourseFile {
  // 雪花算法
  @TableId(type = IdType.ASSIGN_ID)
  private String id;
  private String obsid;
  private String filename;
  private double size;
  private String type;
  private String createtime;
  private String remark;
  @TableField("object_name")
  private String objectName;
  @TableField("bucket_name")
  private String bucketName;
}

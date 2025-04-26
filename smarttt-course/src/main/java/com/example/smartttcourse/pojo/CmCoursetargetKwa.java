package com.example.smartttcourse.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 课程目标关联的kwa
public class CmCoursetargetKwa {
    private String id;      // 标识
    private String kwaId;
    private String targetId;
    private String obsId;   // 课程id
}

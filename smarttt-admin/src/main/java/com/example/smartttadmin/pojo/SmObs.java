package com.example.smartttadmin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教学单位管理
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmObs {

    private String id;//标识
    private String pid;//父节点标识
    private int orderno;//顺序号
    private String name;//名称
    private String innerno;//层级码
    private String obsPath;//全路径
    private String remark;//备注

}

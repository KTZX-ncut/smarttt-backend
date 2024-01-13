package com.example.smartttadmin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StUsers {
    private Integer id;
    private String username;
    private String loginname;
    private String pwd;
    private String phone;
    private String status;
    private String catelog;
    private  String remark;
    private String  headimage;
    private String createtime;
    private String by1;
    private String by2;
}

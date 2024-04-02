package com.example.smartttadmin.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StUsers {
    private String id;
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

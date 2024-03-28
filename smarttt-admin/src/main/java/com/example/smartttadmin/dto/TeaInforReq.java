package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeaInforReq {
    private String id;
    private String roleid;
    private String obsid;
    private long obsdeep;
    private String catelog;
}

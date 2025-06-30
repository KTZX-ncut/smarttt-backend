package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.StUsers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInforReq {
    private String id;
    private String roleid;
    private String obsid;
    private long obsdeep;
    private String catelog;
    private String termid;

    public UserInforReq(StUsers stUsers) {
        this.id = stUsers.getId();
        this.catelog = stUsers.getCatelog();
    }
}

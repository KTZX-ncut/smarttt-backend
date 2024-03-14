package com.example.smartttadmin.dto;

import com.example.smartttadmin.pojo.StUsers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 返回的学院列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObsResponse {
    String id;
    String levelcode;
    String obsname;
    List<StUsers> stUsersList;
}

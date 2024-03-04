package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学期管理信息列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TermsResponse {

    private String id;//学期的id
    private String termName;//学期名称

}
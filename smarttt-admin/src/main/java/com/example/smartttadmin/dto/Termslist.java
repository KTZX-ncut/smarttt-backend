package com.example.smartttadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 学期管理信息列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Termslist {

    private String id;//学期的id
    private String termName;//学期名称

}
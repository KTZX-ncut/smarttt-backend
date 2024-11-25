package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmCheckitem;

import java.util.List;

public interface CmCheckitemService {
    /**
     *创建一个考核项
     */
    Result createOneCheckitem(CmCheckitem cmCheckitem, String courseid);
    /**
     *批量删除考核项
     */
    Result deleteCheckitemByIDs(List<String> ids);
    /**
     *更新考核项
     */
    Result upgradeOneCheckitemByID(String id);
    /**
     *获取考核项列表
     */
    Result getCheckitemList(Token token);
    /**
     *更新考核任务
     */

    Result changeCheckitemTaskTrue(String id);

    Result changeCheckitemTaskFalse(List<String> ids);
    /**
     *更新考核项名称
     */
    Result changeCheckitemName(String id, String name);

    /**
     *更新备注
     */
    Result changeCheckitemRemark(String id,String remark);
}

package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.SmObs;

import java.util.List;

public interface SmObsService {
    Result getAllCollageList();

    /**
     * 新建一个机构（所有的都试用）
     * @param smObs 新建机构
     * @return ...
     */
    Result createOneObs(SmObs smObs);
    Result deleteObsByID(String id);
    Result getObsTree();
    Result getPersonnelRosterByObsIDAndCatelog(String obsid,String catelog);

}

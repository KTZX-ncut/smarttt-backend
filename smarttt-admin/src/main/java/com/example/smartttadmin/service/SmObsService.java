package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.pojo.CmClass;
import com.example.smartttadmin.pojo.CmProfession;
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
    Result deleteOneObsByID(String id);

    /**
     * 只能删叶子结点
     * @param ids
     * @return
     */
    Result deleteObssByIDS(List<String> ids);
    Result getObsTree();
    Result getPersonnelRosterByObsIDAndCatelog(String obsid,String catelog);
    Result createOnePersonnelRoster(PersonnelRoster personnelRoster);

    Result upgradeOneObsByID(String id);

    Result getAllObsList(String ObsID);

    Result getAllProfessionList(String ObsID);

    Result createOneProfession(CmProfession cmProfession);

    Result getClassList(String ObsID);

    Result createOneClass(CmClass cmClass);

    Result updateClass(CmClass cmClass);

    Result updateOneObsByID(SmObs smObs);

    Result getObsRPList(String obsid);

    String upToTeacherObs(Token token);

    Result updateOneProfession(CmProfession cmProfession);
}

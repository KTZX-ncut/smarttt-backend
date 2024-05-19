package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.UserInforReq;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StRoleUser;
import com.example.smartttadmin.pojo.StUsers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StUsersService {
    Result createOneRP(StRoleUser stRoleUser);

    /**
     * 登录
     *
     * @param loginReq ...
     * @return ...
     */
    Result login(LoginReq loginReq);

    /**
     * 需修改为判断是否成立
     *
     * @param ids
     * @return
     */
    Result deleteUsersByIDs(List<String> ids);

    List<PersonnelRoster>importTeacherAndStudentExcel(MultipartFile file) throws IOException;


    Result getUserInfor(UserInforReq userInforReq);

    Result deleteRP(StRoleUser stRoleUser);

    Result updateOnePersonnelRoster(PersonnelRoster personnelRoster);

    Result getStudentByClassID(String id);

    Result getStudentInfor(StUsers data);
}

package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.LoginReq;
import com.example.smartttadmin.dto.PersonnelRoster;
import com.example.smartttadmin.dto.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StUsersService {
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
}

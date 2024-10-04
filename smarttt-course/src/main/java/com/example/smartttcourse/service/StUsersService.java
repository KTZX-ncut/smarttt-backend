package com.example.smartttcourse.service;

import com.example.smartttcourse.dto.StudentDto;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.pojo.StRoleUser;

import java.util.List;

public interface StUsersService {
    Result deleteRP(StRoleUser stRoleUser);

    Result createOneRP(StRoleUser stRoleUser);

    String getUsernameById(String usersid);

    List<StudentDto> getAllStudentByObsID(String id);

    String getloginNameById(String usersid);
}

package com.example.smartttcourse.service;

import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.pojo.StRoleUser;

public interface StUsersService {
    Result deleteRP(StRoleUser stRoleUser);

    Result createOneRP(StRoleUser stRoleUser);
}

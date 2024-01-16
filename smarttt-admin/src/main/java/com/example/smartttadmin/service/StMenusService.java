package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.Result;

public interface StMenusService {
    Result getMenusList(LoginHomeReq loginHomeReq);
}

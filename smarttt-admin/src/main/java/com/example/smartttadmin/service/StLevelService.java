package com.example.smartttadmin.service;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StLevel;

public interface StLevelService {
    Result getSchoolInfor();

    Result updateLevel(StLevel stLevel);
}

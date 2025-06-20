package com.example.smartttadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.pojo.StLevel;

public interface StLevelService extends IService<StLevel> {
    Result getSchoolInfor();

    Result updateLevel(StLevel stLevel);

    long getObsLevel(String number);
}

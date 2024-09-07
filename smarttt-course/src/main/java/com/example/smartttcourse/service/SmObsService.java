package com.example.smartttcourse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.smartttcourse.exception.res.Result;
import com.example.smartttcourse.pojo.SmObs;

public interface SmObsService extends IService<SmObs> {
    String getSchoolObs();

    Result getObsRPList(String obsID);
}

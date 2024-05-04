package com.example.smartttcourse.service;

import com.example.smartttcourse.dto.Result;

public interface SmObsService {
    String getSchoolObs();

    Result getObsRPList(String obsID);
}

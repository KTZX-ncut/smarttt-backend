package com.example.smartttevaluation.service;

import com.example.smartttevaluation.dto.Result;
import com.example.smartttevaluation.pojo.CmCheckitem;

import java.util.List;

public interface CmCheckitemService {

    Result createOneCheckitem(CmCheckitem cmCheckitem, String courseid);

    Result deleteCheckitemByIDs(List<String> ids);

    Result upgradeOneCheckitemByID(String id);

    Result getCheckitemList(String ObsID);
}

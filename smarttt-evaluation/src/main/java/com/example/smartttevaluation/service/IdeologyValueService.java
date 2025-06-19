package com.example.smartttevaluation.service;


import com.example.smartttevaluation.dto.IdeologyValueReq;
import com.example.smartttevaluation.pojo.IdeologyValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdeologyValueService {
    Boolean addIdeologyValue(IdeologyValue ideologyValue);

    Integer countIdeologyByVname(String vname);

    List<IdeologyValue> getIdeologyValueList();

    IdeologyValue getIdeologyValueById(Long id);

    List<IdeologyValue> getIdeologyValueListByCondition(IdeologyValueReq ideologyValueReq);

    Boolean updateIdeologyValue(IdeologyValue ideologyValue);

    Boolean delIdeologyValueByIdList(List<Long> idList);
}

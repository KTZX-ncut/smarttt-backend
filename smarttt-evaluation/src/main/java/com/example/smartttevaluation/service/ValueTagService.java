package com.example.smartttevaluation.service;


import com.example.smartttevaluation.dto.KwaBindReq;
import com.example.smartttevaluation.dto.KwaValueTagBindReq;
import com.example.smartttevaluation.pojo.ValueTag;

import java.util.List;

public interface ValueTagService {
    List<ValueTag> getAllValue();

    List<ValueTag> getValueByTypeId(Long typeId);

    Boolean deleteValueTagByTypeId(List<Long> typeIdList);

    Integer getValueByName(String name);

    Boolean createValue(ValueTag valueTag);

    Boolean update(ValueTag valueTag);

    Boolean delete(List<Long> idList);

    List<KwaValueTagBindReq> validTagIdAndName(List<Long> tagIdList);

    Integer countKwaByKwaId(String kwaId);

    Boolean bindKwaAndValue(KwaBindReq kwaBindReq);

    String getValueTagListJson(String kwaId);

    void decreaseValueTagCalCount(List<Long> ids);

    void increaseValueTagCalCount(List<Long> newIds);
}

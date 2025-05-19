package com.example.smartttevaluation.mapper;

import cn.hutool.json.JSON;
import com.example.smartttevaluation.dto.KwaValueTagBindReq;
import com.example.smartttevaluation.pojo.ValueTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ValueTagMapper {
    List<ValueTag> getAllValue();

    List<ValueTag> getValueByTypeId(@Param("typeId") Long typeId);

    Boolean deleteValueTagByTypeId(@Param("typeIdList") List<Long> typeIdList);

    Integer getValueByName(@Param("name") String name);

    Boolean createValue(ValueTag valueTag);

    Boolean update(ValueTag valueTag);

    Boolean delete(@Param("idList") List<Long> idList);

    List<KwaValueTagBindReq> validTagIdAndName(@Param("tagIdList") List<Long> tagIdList);

    Integer countKwaByKwaId(@Param("kwaId") String kwaId);

    Boolean bindKwaAndValue(@Param("kwaId") String kwaId, @Param("parse") String parse);

    String getValueTagListJson(String kwaId);

    void decreaseValueTagCalCount(@Param("ids") List<Long> ids);

    void increaseValueTagCalCount(@Param("newIds") List<Long> newIds);

    List<ValueTag> getValueTagByIdList(@Param("idList") List<Long> idList);

    List<ValueTag> getValueTagByTypeIdList(@Param("typeIdList") List<Long> typeIdList);
}

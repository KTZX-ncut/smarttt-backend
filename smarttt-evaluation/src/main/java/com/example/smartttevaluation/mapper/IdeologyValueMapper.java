package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.IdeologyValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 思想价值表Mapper
 */
@Mapper
public interface IdeologyValueMapper {
    int addIdeologyValue(IdeologyValue ideologyValue);

    Integer countIdeologyByVname(@Param("vname") String vname,@Param("classroomId") String classroomId);

    List<IdeologyValue> getIdeologyValueList();

    IdeologyValue getIdeologyValueById(@Param("id") Long id);

    List<IdeologyValue> getIdeologyValueListByCondition(IdeologyValue ideologyValue);

    List<IdeologyValue> getIdeologyValueListByFuzzyCondition(IdeologyValue ideologyValue);

    Boolean updateIdeologyValue(IdeologyValue ideologyValue);

    Boolean delIdeologyValueByIdList(@Param("idList") List<Long> idList);

    Integer countClassroomByClassroomId(@Param("classroomId") String classroomId);
}

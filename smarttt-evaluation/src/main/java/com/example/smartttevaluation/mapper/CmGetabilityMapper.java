package com.example.smartttevaluation.mapper;

import com.example.smartttevaluation.pojo.CmAbility;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CmGetabilityMapper {
    @Select("select id, remark from cm_ability")
    List<CmAbility> getGetability ();
    void deleteGetabilityByIDs(@Param("ids") List<String> ids);

    void updateGetabilityByID(CmAbility cmAbility);
}

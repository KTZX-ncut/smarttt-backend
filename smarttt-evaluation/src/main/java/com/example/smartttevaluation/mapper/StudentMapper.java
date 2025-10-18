package com.example.smartttevaluation.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttevaluation.pojo.SmStudent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<SmStudent> {
}

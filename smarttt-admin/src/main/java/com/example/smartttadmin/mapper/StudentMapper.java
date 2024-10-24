package com.example.smartttadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.smartttadmin.pojo.SmStudent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lunSir
 * @create 2024-10-18 13:13
 */
@Mapper
public interface StudentMapper extends BaseMapper<SmStudent> {
}

package com.example.smartttevaluation.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.smartttevaluation.dto.IdeologyValueReq;
import com.example.smartttevaluation.mapper.IdeologyValueMapper;
import com.example.smartttevaluation.pojo.IdeologyValue;
import com.example.smartttevaluation.service.IdeologyValueService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class IdeologyValueServiceImpl implements IdeologyValueService {

    private IdeologyValueMapper ideologyValueMapper;

    public IdeologyValueServiceImpl(IdeologyValueMapper ideologyValueMapper) {
        this.ideologyValueMapper = ideologyValueMapper;
    }

    @Override
    public Boolean addIdeologyValue(IdeologyValue ideologyValue) {
        ideologyValue.setId(IdWorker.getId(ideologyValue));
        ideologyValue.setCreateTime(LocalDateTime.now());
        ideologyValue.setUpdateTime(LocalDateTime.now());
        if (StrUtil.isBlank(ideologyValue.getRemark())){
            ideologyValue.setRemark("无");
        }
        ideologyValueMapper.addIdeologyValue(ideologyValue);
        return true;
    }

    @Override
    public Integer countIdeologyByVname(String vname,String courseId) {
        return ideologyValueMapper.countIdeologyByVname(vname,courseId);
    }

    @Override
    public List<IdeologyValue> getIdeologyValueList() {
        return ideologyValueMapper.getIdeologyValueList();
    }

    @Override
    public IdeologyValue getIdeologyValueById(Long id) {
        return ideologyValueMapper.getIdeologyValueById(id);
    }

    @Override
    public List<IdeologyValue> getIdeologyValueListByCondition(IdeologyValueReq ideologyValueReq) {
        Boolean fuzzyQuery = ideologyValueReq.getFuzzyQuery();
        List<IdeologyValue> ideologyValueList = null;
        IdeologyValue ideologyValue = new IdeologyValue();
        ideologyValue.setCourseId(ideologyValueReq.getCourseId());
        if (StrUtil.isNotBlank(ideologyValueReq.getVname())){
            ideologyValue.setVname(ideologyValueReq.getVname());
        }
        if (StrUtil.isNotBlank(ideologyValueReq.getRemark())){
            ideologyValue.setRemark(ideologyValueReq.getRemark());
        }
        // if ... 后续好扩展，用实体类去Mapper层查询，做好隔离，防止实体类被污染
        if (Objects.isNull(fuzzyQuery) || !fuzzyQuery){
            // 不支持模糊查询
            ideologyValueList = ideologyValueMapper.getIdeologyValueListByCondition(ideologyValue);
        }else {
            // 支持模糊查询
            ideologyValueList = ideologyValueMapper.getIdeologyValueListByFuzzyCondition(ideologyValue);
        }
        return ideologyValueList;
    }

    @Override
    public Boolean updateIdeologyValue(IdeologyValue ideologyValue) {
        ideologyValue.setUpdateTime(LocalDateTime.now());
        return ideologyValueMapper.updateIdeologyValue(ideologyValue);
    }

    @Override
    public Boolean delIdeologyValueByIdList(List<Long> idList) {
        return ideologyValueMapper.delIdeologyValueByIdList(idList);
    }

    @Override
    public Integer countCourseByCourseId(String courseId) {
        return ideologyValueMapper.countCourseByCourseId(courseId);
    }
}

package com.example.smartttadmin.service.impl;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.SchoolInforReq;
import com.example.smartttadmin.mapper.SmObsMapper;
import com.example.smartttadmin.mapper.StLevelMapper;
import com.example.smartttadmin.pojo.StLevel;
import com.example.smartttadmin.service.StLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StLevelServiceImpl implements StLevelService {
    @Autowired
    private SmObsMapper smObsMapper;
    @Autowired
    private StLevelMapper stLevelMapper;
    @Override
    public Result getSchoolInfor() {
        SchoolInforReq schoolInforReq = smObsMapper.getSchoolObs();
        schoolInforReq.setStLevelList(stLevelMapper.getStLevel());
        for(StLevel stLevel: schoolInforReq.getStLevelList()){
            if(stLevel.getObsdeep()!=0){
                stLevel.setObsdeep(1);
            }
        }
        return Result.success(schoolInforReq);
    }

    @Override
    public Result updateLevel(StLevel stLevel) {
        if(stLevel.getLevelname()!=null || stLevel.getTeacher()!=null || stLevel.getStudent() != null)
            stLevelMapper.updateStLevel(stLevel);
        if(Objects.equals(stLevel.getStudent(), "1")){
            stLevelMapper.updateOtherStudent(stLevel.getId());
        }
        if(Objects.equals(stLevel.getTeacher(), "1")){
            stLevelMapper.updateOtherTeacher(stLevel.getId());
        }
        long obsDeep = stLevel.getObsdeep();
        if(obsDeep != 0) {
            if(obsDeep == -1)obsDeep = 0;
            stLevelMapper.updateLevelObsdeep(obsDeep,stLevel.getId());
            List<StLevel> stLevelList = stLevelMapper.getStLevelByStatue();
            long sum = 0;
            for(StLevel stLevel1 : stLevelList){
                sum++;
                stLevelMapper.updateAllLevel(stLevel1.getId(),sum);
            }
        }
        return Result.success();
    }
}

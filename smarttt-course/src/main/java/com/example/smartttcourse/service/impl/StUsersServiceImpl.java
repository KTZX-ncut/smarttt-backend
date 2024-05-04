package com.example.smartttcourse.service.impl;

import com.example.smartttcourse.dto.Result;
import com.example.smartttcourse.mapper.SmObsMapper;
import com.example.smartttcourse.mapper.StUsersMapper;
import com.example.smartttcourse.pojo.StRoleUser;
import com.example.smartttcourse.service.StUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.smartttcourse.Utils.CommonFunctions.generateEnhancedID;

@Service
public class StUsersServiceImpl implements StUsersService {
    @Autowired
    private SmObsMapper smObsMapper;
    @Autowired
    private StUsersMapper stUsersMapper;

    @Override
    public Result deleteRP(StRoleUser stRoleUser) {
        stUsersMapper.deletePRByObsIDAndUserID(stRoleUser);
        return Result.success();
    }

    @Override
    public Result createOneRP(StRoleUser stRoleUser) {
        try {
            stRoleUser.setId(generateEnhancedID("st_roleuser"));
            stRoleUser.setObsdeep(-1);
            stRoleUser.setCreatetime(LocalDate.now().toString());
            stUsersMapper.createOneRoleUser(stRoleUser);
        }catch (NullPointerException e)
        {
            return Result.error(404,"新建失败");
        }
        return Result.success();
    }
}

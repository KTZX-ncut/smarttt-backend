package com.example.smartttadmin.serive.Impl;

import com.example.smartttadmin.mapper.StUsersMapper;
import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.serive.StUsersSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StUsersSeriveImpl implements StUsersSerive {
    @Autowired
    private StUsersMapper stUsersMapper;
    @Override
    public StUsers login(StUsers stUsers){
        return stUsersMapper.Selectlogin(stUsers);
    }
}

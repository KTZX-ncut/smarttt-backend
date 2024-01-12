package com.example.smartttadmin.controller;

import com.example.smartttadmin.pojo.StUsers;
import com.example.smartttadmin.serive.Impl.StUsersSeriveImpl;
import com.example.smartttadmin.serive.StUsersSerive;
import com.sun.java.browser.plugin2.liveconnect.v1.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private StUsersSerive stUsersSerive;
    @PostMapping("/login")
    public String login(@RequestBody StUsers stUsers){
        StUsers stUsers1 = stUsersSerive.login(stUsers);
        if(stUsers1 == null)return "error";
        return "success";
    }
}
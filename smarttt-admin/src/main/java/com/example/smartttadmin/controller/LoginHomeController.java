package com.example.smartttadmin.controller;

import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.service.StMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class LoginHomeController {
   @Autowired
    private StMenusService stMenusService;
    @PostMapping("/superadminhome")
    public Result SuperAdminHome(@RequestBody LoginHomeReq loginHomeReq){

        return stMenusService.getMenusList(loginHomeReq);
    }

}

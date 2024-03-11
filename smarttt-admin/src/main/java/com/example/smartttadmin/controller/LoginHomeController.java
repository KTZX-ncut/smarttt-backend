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
@RequestMapping("/homes")
public class LoginHomeController {
   @Autowired
    private StMenusService stMenusService;

    /**
     * 教师类型用户登录到首页
     * @param loginHomeReq ...
     * @return Result
     */
    @PostMapping("/teacherhome")
    public Result TeacherHome(@RequestBody LoginHomeReq loginHomeReq){
        return stMenusService.getMenusList(loginHomeReq);
    }

}

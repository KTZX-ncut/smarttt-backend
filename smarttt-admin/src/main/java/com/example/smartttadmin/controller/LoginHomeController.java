package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.LoginHomeReq;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.service.StMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;

@RestController
@RequestMapping("/homes")
public class LoginHomeController {
   @Autowired
    private StMenusService stMenusService;

    /**
     * 教师类型用户登录到首页
     * @return Result
     */
    @PostMapping("/teacherhome")
    @AuthRequired(type = "admin",menu = "531500340-0f2888b9-ecaf-49a7-b175-7ae00e14ae65")
    public Result TeacherHome(HttpServletRequest request){
//        return stMenusService.getMenusList(loginHomeReq);
        Token token = getTokenFromContext();
        return stMenusService.getMenusList(token.getRoleid());
    }

}

package com.example.smartttadmin.controller;

import com.example.smartttadmin.Utils.AuthRequired;
import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import com.example.smartttadmin.service.StMenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.smartttadmin.Utils.AuthorizationAspect.getTokenFromContext;

/**
 * 教学目标设定
 */
@RestController
@RequestMapping("/sysmangt/teachinggoalsetting")
public class TeachingGoalController {
    @Autowired
    private StMenusService stMenusService;
    @GetMapping("")
    @AuthRequired(type = "admin",menu = "531500340-037f3055-b672-4c03-8452-5773069b37bf",isReadOnly = true)
    public Result getTeachingGoal(HttpServletRequest request){
        Token token = getTokenFromContext();
        return stMenusService.getTeachingGoal(token.getObsid());
    }
}

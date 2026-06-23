package com.example.smartttexam.controller;

import com.example.smartttexam.Utils.JwtTokenUtils;
import com.example.smartttexam.dto.Result;
import com.example.smartttexam.dto.Token;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.smartttexam.Utils.CommonFunctions.TokenSK;

/**
 * 测试辅助：生成测试用 JWT Token
 * 正式上线前请删除此 Controller
 */
@Api(tags = "测试工具")
@RestController
@RequestMapping("/exam/test")
public class TestTokenController {

    @GetMapping("/token")
    public Result getTestToken(
            @RequestParam(required = false, defaultValue = "8aea800182e80d000182e886980c0d7a") String obsid,
            @RequestParam(required = false, defaultValue = "516761049-9a741546-0b55-489b-9dc4-31789ee07153") String roleid,
            @RequestParam(required = false, defaultValue = "1") String userid) {
        Token token = new Token();
        token.setId(userid);
        token.setRoleid(roleid);
        token.setObsid(obsid);
        token.setObsdeep(103);

        String jwt = JwtTokenUtils.getToken(token, TokenSK);
        return Result.success(jwt);
    }
}

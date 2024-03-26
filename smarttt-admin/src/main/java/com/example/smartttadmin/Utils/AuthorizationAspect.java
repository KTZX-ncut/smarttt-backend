package com.example.smartttadmin.Utils;

import com.example.smartttadmin.dto.Result;
import com.example.smartttadmin.dto.Token;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

import static com.example.smartttadmin.Utils.JwtTokenUtils.parseToken;

@Aspect
@Component
public class AuthorizationAspect {

    private static final ThreadLocal<Token> tokenThreadLocal = new ThreadLocal<>();

    @Before("@annotation(authRequired) && args(.., request)")
    public Result beforeMethodWithAuthRequired(AuthRequired authRequired, HttpServletRequest request) {
        String token = extractTokenFromRequest(request);
        Token token1 = parseToken(token,"123456");
        // 存储 token 到上下文中
        tokenThreadLocal.set(token1);
        // 在这里可以执行其他鉴权逻辑
        String value = authRequired.type();
        if ("admin".equals(value)) {
            // 执行管理员鉴权逻辑
            String roleid = authRequired.roleid();
            if(!Objects.equals(roleid, token1.getRoleid()))return Result.error("用户访问错误");
            return null;
        } else if ("user".equals(value)) {
            // 执行普通用户鉴权逻辑
            System.out.println("Performing user authorization");
        } else {
            // 默认鉴权逻辑
            System.out.println("Performing default authorization");
        }
        return null;
    }

    // 从 HTTP 请求中提取出 token
    public String extractTokenFromRequest(HttpServletRequest request) {
        // 从请求头或请求参数中提取出 token
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            token = request.getParameter("token");
        }
        return token;
    }

    // 获取上下文中存储的 token
    public static Token getTokenFromContext() {
        return tokenThreadLocal.get();
    }
}


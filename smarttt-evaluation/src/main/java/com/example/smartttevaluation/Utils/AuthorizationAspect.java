package com.example.smartttevaluation.Utils;

import com.example.smartttevaluation.dto.Token;
import com.example.smartttevaluation.mapper.StMenusMapper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static com.example.smartttevaluation.Utils.CommonFunctions.TokenSK;
import static com.example.smartttevaluation.Utils.JwtTokenUtils.parseToken;

@Aspect
@Component
public class AuthorizationAspect {
    @Autowired
    private StMenusMapper stMenusMapper;

    private static final ThreadLocal<Token> tokenThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> logIdThreadLocal = new ThreadLocal<>();

    @Before("@annotation(authRequired) && args(.., request)")
    public void beforeMethodWithAuthRequired(AuthRequired authRequired, HttpServletRequest request) {
        String stringToken = extractTokenFromRequest(request);
        String logId = extractLogIdFromRequest(request);
        Token token = parseToken(stringToken,TokenSK);
        // 存储 stringToken 到上下文中
        tokenThreadLocal.set(token);
        logIdThreadLocal.set(logId);
        // 在这里可以执行其他鉴权逻辑
        String type = authRequired.type();
        if ("admin".equals(type)) {
            // 执行管理员鉴权逻辑
            List<String> statueList = stMenusMapper.getStatueInRoleUser(token.getRoleid(),authRequired.menu());
            if(statueList.size()!=1)throw new RuntimeException("无访问权限");
            String statue = statueList.get(0);
            if(Objects.equals(statue, "3") || Objects.equals(statue, "2") && !authRequired.isReadOnly())
                throw new RuntimeException("无访问权限");
        } else if ("user".equals(type)) {
            // 执行普通用户鉴权逻辑
            System.out.println("Performing user authorization");
        } else {
            // 默认鉴权逻辑
            System.out.println("Performing default authorization");
        }
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
    // 从 HTTP 请求中提取出 logId

    public String extractLogIdFromRequest(HttpServletRequest request) {
        // 从请求头或请求参数中提取出 token
        String LogId = request.getHeader("logid");
        if (LogId == null || LogId.isEmpty()) {
            LogId = request.getParameter("logid");
        }
        return LogId;
    }
    // 获取上下文中存储的 logId
    public static String getLogIdFromContext() {
        return logIdThreadLocal.get();
    }
    // 获取上下文中存储的 token
    public static Token getTokenFromContext() {
        return tokenThreadLocal.get();
    }
}


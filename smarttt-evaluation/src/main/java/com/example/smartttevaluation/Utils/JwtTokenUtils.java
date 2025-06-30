package com.example.smartttevaluation.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.smartttevaluation.dto.Token;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtils {

    /**
     * 生成token
     */
    public static String getToken(Token token, String secretKey) {
        return JWT.create()
                .withClaim("id", token.getId())
                .withClaim("roleid", token.getRoleid())
                .withClaim("obsid", token.getObsid())
                .withClaim("obsdeep", token.getObsdeep())
                .withClaim("termid",token.getTermid())
                .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)) // 设置过期时间为2小时后
                .sign(Algorithm.HMAC256(secretKey)); // 签名使用的密钥
    }

    /**
     * 解析token
     */
    public static Token parseToken(String jwtToken, String secretKey) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(jwtToken);
        Token token = new Token();
        token.setId(decodedJWT.getClaim("id").asString());
        token.setRoleid(decodedJWT.getClaim("roleid").asString());
        token.setObsid(decodedJWT.getClaim("obsid").asString());
        token.setObsdeep(decodedJWT.getClaim("obsdeep").asLong());
        token.setTermid(decodedJWT.getClaim("termid").asString());
        // 返回解析出的 Token 对象
        return token;
    }

}

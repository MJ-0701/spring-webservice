package com.example.springwebservice.domain.user.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springwebservice.domain.user.domain.User;
import com.example.springwebservice.domain.user.web.dto.res.VerifyResult;

import java.time.Instant;

public class JWTUtil {

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("jack");
    private static final long AUTH_TIME = 20*60; // 20분
    private static final long REFRESH_TIME = 60*60*24*7; // 1주일


    public static String makeAuthToken(User user){
        return JWT.create()
                .withSubject(user.getUserName())
                .withClaim("exp", Instant.now().getEpochSecond()+AUTH_TIME)
                .sign(ALGORITHM);
    }

    public static String refreshAuthToken(User user){
        return JWT.create()
                .withSubject(user.getUserName())
                .withClaim("exp", Instant.now().getEpochSecond()+REFRESH_TIME)
                .sign(ALGORITHM);
    }

    public static VerifyResult verify(String token){
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            return VerifyResult.builder()
                    .success(true)
                    .userName(verify.getSubject())
                    .build();
        }catch (Exception e){
            DecodedJWT decodedJWT = JWT.decode(token);
            return VerifyResult.builder()
                    .success(false)
                    .userName(decodedJWT.getSubject())
                    .build();
        }
    }

}

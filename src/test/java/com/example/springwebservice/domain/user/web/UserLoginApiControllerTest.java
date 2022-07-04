package com.example.springwebservice.domain.user.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springwebservice.domain.user.domain.User;
import com.example.springwebservice.domain.user.domain.repository.UserRepository;
import com.example.springwebservice.domain.user.service.UserService;
import com.example.springwebservice.domain.user.web.dto.req.UserReqDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;
import java.net.URI;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserLoginApiControllerTest{

    @LocalServerPort
    int port;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void before(){
        userRepository.deleteAll();

        User user = userService.save(
                UserReqDto.builder()
                        .email("jack2718@naver.com")
                        .pwd("1234")
                        .build()
        );


    }


    private void printToken(String token){
        String [] tokens = token.split("\\.");
        System.out.println("header :" + new String(Base64.getDecoder().decode(tokens[0])));
        System.out.println("body :" + new String(Base64.getDecoder().decode(tokens[1])));
    }

    @Test
    @DisplayName("okta : jjwt")
    void jwtTest(){
        String oktaToken = Jwts.builder().addClaims(
                Map.of("name", "mj", "price", 3000)
        ).signWith(SignatureAlgorithm.HS256,"jack")
                .compact();
        System.out.println(oktaToken);
        printToken(oktaToken);

        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey("jack").parseClaimsJws(oktaToken);
        System.out.println(tokenInfo);

    }

    @Test
    @DisplayName("oauth : jwt")
    void jwtTokenTest(){
        // 라이브러리가 다를 경우 사인 키값을 같게 설정 해준다.
        byte[] SEC_KEY = DatatypeConverter.parseBase64Binary("jack");

        String oauth0Token = JWT.create().withClaim("name", "jack").withClaim("price", 3000)
                .sign(Algorithm.HMAC256(SEC_KEY));
                //.sign(Algorithm.HMAC256("jack"));

        System.out.println(oauth0Token);
        printToken(oauth0Token);

        //DecodedJWT verifier = JWT.require(Algorithm.HMAC256("jack")).build().verify(oauth0Token);
        DecodedJWT verifier = JWT.require(Algorithm.HMAC256(SEC_KEY)).build().verify(oauth0Token);
        System.out.println(verifier.getClaims());

        Jws<Claims> tokenInfo = Jwts.parser().setSigningKey(SEC_KEY).parseClaimsJws(oauth0Token);
        System.out.println(tokenInfo);


    }

    @Test
    @DisplayName("만료시간 태스트")
    void jwtExpTest() throws InterruptedException {
        final Algorithm AL = Algorithm.HMAC256("jack");

        String token = JWT.create().withSubject("jack1234")
                .withNotBefore(new Date(System.currentTimeMillis()+ 1000)) // 1초가 지난 후 사용 가능
                .withExpiresAt(new Date(System.currentTimeMillis()+ 3000)) // 3초동안 사용 가능 -> 이경우엔 유효 시간이 2초
                .sign(AL);

        //Thread.sleep(2000);

        try { // 토큰을 즉시 가져오지 못하므로 정보를 알아낼 수 없으므로
            DecodedJWT verify = JWT.require(AL).build().verify(token);
            System.out.println(verify.getClaims());
        }catch(Exception e){ // catch 에서 강제로 정보를 알아 낸다. (이런 경우가 필요할 수 있음.)
            System.out.println("유효하지 않은 토큰");
            DecodedJWT decode = JWT.decode(token);
            System.out.println(decode.getClaims());
        }


    }

    @DisplayName("jwt Login Test")
    @Test
    void test_1(){

        RestTemplate client = new RestTemplate();

        String url = "http://localhost:" + 8080 + "api/v1/user/login";
        HttpEntity<UserReqDto> body = new HttpEntity<>(
                UserReqDto.builder()
                        .email("jack2718@naver.com")
                        .pwd("1234")
                        .build()
        );
        ResponseEntity<User> resp1 = client.exchange(url, HttpMethod.POST,body, User.class);
        System.out.println(resp1.getHeaders().get(HttpHeaders.AUTHORIZATION).get(9));
        System.out.println(resp1.getBody());
    }

}
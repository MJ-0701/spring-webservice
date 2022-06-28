package com.example.springwebservice.domain.user.web.dto.req;

import com.example.springwebservice.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Getter
@NoArgsConstructor
public class UserReqDto {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

    private String pwd;

    private String email;

    @Builder
    public UserReqDto(String userId, String userName, String pwd, String email){
        this.userId = userId;
        this.userName = userName;
        this.pwd = pwd;
        this.email = email;
    }

    public User toEntity(){
        return User.builder()
                .userId(userId)
                .userName(userName)
                .pwd(pwd)
                .email(email)
                .build();
    }
}

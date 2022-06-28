package com.example.springwebservice.domain.user.web.dto.res;

import com.example.springwebservice.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResDto {

    private Long idx;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

    private String email;

    public UserResDto(User entity){
        this.idx = entity.getIdx();
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
        this.email = entity.getEmail();
    }

}

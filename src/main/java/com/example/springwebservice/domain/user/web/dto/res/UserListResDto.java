package com.example.springwebservice.domain.user.web.dto.res;

import com.example.springwebservice.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserListResDto {

    private Long idx;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_id")
    private String userId;

    private String email;

    public UserListResDto(User entity){
        this.idx = entity.getIdx();
        this.userName = entity.getUserName();
        this.userId = entity.getUserId();
        this.email = entity.getEmail();
    }

}

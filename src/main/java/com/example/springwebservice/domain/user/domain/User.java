package com.example.springwebservice.domain.user.domain;

import com.example.springwebservice.global.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;


    @Column(unique = true)
    private String userId;


    private String userName;

    private String pwd;

    @Email
    @Column(unique = true)
    private String email;

    @Builder
    private User(String userId, String userName, String pwd, String email){
        this.email = email;
        this.userId = userId;
        this.userName = userName;
        this.pwd = pwd;
    }

    public User update(String userName, String pwd, String email){
        this.userName = userName;
        this.pwd = pwd;
        this.email = email;
        return  this;
    }


}

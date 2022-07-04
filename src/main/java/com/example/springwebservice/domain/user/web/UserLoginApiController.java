package com.example.springwebservice.domain.user.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/login")
public class UserLoginApiController {


    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String greeting(){
        return "hello";
    }

}

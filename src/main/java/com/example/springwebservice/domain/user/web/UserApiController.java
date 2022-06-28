package com.example.springwebservice.domain.user.web;

import com.example.springwebservice.domain.user.domain.User;
import com.example.springwebservice.domain.user.service.UserService;
import com.example.springwebservice.domain.user.web.dto.req.UserReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;



    @PostMapping("/save")
    public ResponseEntity<User> userSave(@RequestBody UserReqDto userReqDto){

        return ResponseEntity.ok(userService.save(userReqDto));
    }
}

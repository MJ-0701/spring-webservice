package com.example.springwebservice.domain.user.web;

import com.example.springwebservice.domain.user.domain.User;
import com.example.springwebservice.domain.user.service.UserService;
import com.example.springwebservice.domain.user.web.dto.req.UserReqDto;
import com.example.springwebservice.domain.user.web.dto.res.UserListResDto;
import com.example.springwebservice.domain.user.web.dto.res.UserResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> userSave(@RequestBody UserReqDto userReqDto){

        return ResponseEntity.ok(userService.save(userReqDto));
    }

    @GetMapping("/read/{idx}")
    public ResponseEntity<UserResDto> getUserInfo(@PathVariable Long idx){

        return ResponseEntity.ok(userService.read(idx));
    }

    @GetMapping("/user-list")
    public ResponseEntity<List<UserListResDto>> getUserList(){
        return ResponseEntity.ok(userService.readAll());
    }

    @PutMapping("/update/{idx}")
    public ResponseEntity<User> update(@PathVariable Long idx, @RequestBody UserReqDto userReqDto){
        return ResponseEntity.ok(userService.update(idx,userReqDto));
    }

    @DeleteMapping("/delete/{idx}")
    public ResponseEntity<Long> delete(@PathVariable Long idx){
        userService.delete(idx);
        return ResponseEntity.ok(idx);
    }

}

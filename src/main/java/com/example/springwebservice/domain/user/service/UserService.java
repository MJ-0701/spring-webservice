package com.example.springwebservice.domain.user.service;

import com.example.springwebservice.domain.user.domain.User;
import com.example.springwebservice.domain.user.domain.repository.UserRepository;
import com.example.springwebservice.domain.user.web.dto.req.UserReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // C
    public User save(UserReqDto userReqDto){
        return userRepository.save(userReqDto.toEntity());

    }

    // R
//    public User read(){
//
//    }
//
//    public User readAll(){
//
//    }
//
//    // U
//
//    public Long update(){
//
//    }
//
//    // D
//
//    public void delete(){
//
//    }

}

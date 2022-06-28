package com.example.springwebservice.domain.user.service;

import com.example.springwebservice.domain.user.domain.User;
import com.example.springwebservice.domain.user.domain.repository.UserRepository;
import com.example.springwebservice.domain.user.web.dto.req.UserReqDto;
import com.example.springwebservice.domain.user.web.dto.res.UserListResDto;
import com.example.springwebservice.domain.user.web.dto.res.UserResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // C
    @Transactional
    public User save(UserReqDto userReqDto){
        return userRepository.save(userReqDto.toEntity());

    }

     //R
    @Transactional
    public UserResDto read(Long idx){
        User entity = userRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id =" + idx));
        return  new UserResDto(entity);

    }

    @Transactional(readOnly = true)
    public List<UserListResDto> readAll(){
        return userRepository.findAll().stream()
                .map(UserListResDto::new)
                .collect(Collectors.toList());
    }

    // U
    @Transactional
    public User update(Long idx, UserReqDto userReqDto){
        User entity = userRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id =" + idx));
        return entity.update(userReqDto.getUserName(), userReqDto.getPwd(), userReqDto.getEmail());


    }

    // D
    public void delete(Long idx){
        User entity = userRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id =" + idx));
        userRepository.delete(entity);
    }

}

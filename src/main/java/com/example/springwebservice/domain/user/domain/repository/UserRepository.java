package com.example.springwebservice.domain.user.domain.repository;

import com.example.springwebservice.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

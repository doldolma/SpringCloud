package com.doldolma.userservice.service;

import com.doldolma.userservice.dto.UserDto;
import com.doldolma.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserbyAll();
    UserDto getUserDetailsByEmail(String email);
}

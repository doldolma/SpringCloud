package com.doldolma.userservice.service;

import com.doldolma.userservice.dto.UserDto;
import com.doldolma.userservice.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserbyAll();
}

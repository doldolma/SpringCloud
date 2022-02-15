package com.doldolma.userservice.service;

import com.doldolma.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}

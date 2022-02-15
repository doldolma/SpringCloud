package com.doldolma.userservice.dto;

import java.util.Date;
import lombok.Data;

@Data
public class UserDto {

    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date date;

    private String encryptedPwd;
}

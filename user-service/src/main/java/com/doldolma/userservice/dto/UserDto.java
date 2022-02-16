package com.doldolma.userservice.dto;

import com.doldolma.userservice.vo.ResponseOrder;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class UserDto {

    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date date;

    private String encryptedPwd;

    private List<ResponseOrder> orders;
}

package com.fpt.onlineTest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserConnectDto {
    private Integer id;
    private String address;
    private String email;
    private String fullName;
    private String image;
    private String phone;
    private String username;
    private String password;
    private Integer role;
}

package com.fpt.onlineTest.configuration.common;


import com.fpt.onlineTest.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDetailsDto {
    private String username;
    private String fullname;
    private Role role;

    public UserDetailsDto(String username, String fullName, String roleName) {

    }
}

package com.fpt.onlineTest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String imageUser;
    private Page<BlogDto> blogs;
    private Page<CourseDto> courses;
}

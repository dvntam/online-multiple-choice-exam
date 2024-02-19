package com.fpt.onlineTest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherDto {
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String imageTeacher;
    private Page<CourseDto> courses;
}

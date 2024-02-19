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
public class CoursesRegistrationDto {
    private Integer crId;
    private CourseDto courseDto;
    private Page<UserDto> userDto;
}

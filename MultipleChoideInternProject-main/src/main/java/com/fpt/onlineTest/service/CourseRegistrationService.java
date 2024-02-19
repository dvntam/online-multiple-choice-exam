package com.fpt.onlineTest.service;

import com.fpt.onlineTest.dto.CoursesRegistrationDto;
import com.fpt.onlineTest.model.CoursesRegistration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;


public interface CourseRegistrationService {
    // course registration
    CoursesRegistration courseRegistration(CoursesRegistration cr);

    //Cancel course registration
    void cancelRegistration( Integer userId, Integer courseId);
    // get course user registration
    CoursesRegistrationDto getCourseUsers(Integer courseId, Pageable pageable);
}

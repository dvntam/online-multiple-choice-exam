package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.dto.CoursesRegistrationDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.Blog;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.CoursesRegistration;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.reponsitory.CourseRepository;
import com.fpt.onlineTest.reponsitory.CoursesRegistrationRepository;
import com.fpt.onlineTest.reponsitory.UserReponsitory;
import com.fpt.onlineTest.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    @Autowired
    private CoursesRegistrationRepository coursesRegistrationRepository;
    @Autowired
    private UserReponsitory userReponsitory;
    @Autowired
    private CourseRepository courseRepository;


    @Override
    public CoursesRegistration courseRegistration(CoursesRegistration cr) {
        Optional<Course> existCourseOption = courseRepository.findById(cr.getCourse().getCourseId());
        if (existCourseOption.isPresent()){
            Course existCourse = existCourseOption.get();
            Integer curNumStudent = existCourseOption.get().getNumberStudent();
            curNumStudent++;
            existCourse.setNumberStudent(curNumStudent);

            courseRepository.save(existCourse);
        }
        return coursesRegistrationRepository.save(cr);
    }

    @Override
    public void cancelRegistration( Integer userId, Integer courseId) {
        Integer SubscribeCourseId = coursesRegistrationRepository.SubscribeCourseId(userId,courseId);
        Optional<CoursesRegistration> existCR = coursesRegistrationRepository.findById(SubscribeCourseId);
        if (existCR.isPresent()){
            Optional<Course> existCourseOption = courseRepository.findById(existCR.get().getCourse().getCourseId());
            if (existCourseOption.isPresent()){
                Course existCourse = existCourseOption.get();
                Integer curNumStudent = existCourseOption.get().getNumberStudent();
                curNumStudent--;
                existCourse.setNumberStudent(curNumStudent);

                courseRepository.save(existCourse);
            }
        }
        coursesRegistrationRepository.deleteById(SubscribeCourseId);
    }

    //
    @Override
    public CoursesRegistrationDto getCourseUsers(Integer courseId, Pageable pageable) {
        List<Integer> crUserIdList = coursesRegistrationRepository.findUserIdsByCourseId(courseId);
        Page<User> userPage = userReponsitory.findCourseSUsers(crUserIdList, pageable);
        CoursesRegistrationDto crDto = new CoursesRegistrationDto();
        crDto.setUserDto(userPage.map(this::mapUserToDto));
        return crDto;
    }
    public UserDto mapUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setImageUser(user.getImageUser());
        return userDto;
    }
}

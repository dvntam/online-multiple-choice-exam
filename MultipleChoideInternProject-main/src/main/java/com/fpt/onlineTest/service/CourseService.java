package com.fpt.onlineTest.service;

import com.fpt.onlineTest.dto.CourseDto;
import com.fpt.onlineTest.dto.TeacherDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    //    create course
    Course newCourse(Course course);

    //    delete course by id
    void deleteCourseById(Integer courseId);

    //    update course
    Course updateCourse(Integer courseId, Course course);

    //    get all course
    Page<CourseDto> getAll(Pageable pageable);

    //    get teacher course
    TeacherDto getCoursesByTeacherId(Integer teacherId, Pageable pageable, Optional<Teacher> teacher);

    //    get student courses
    UserDto getCoursesByUserIdtId(Integer studentId, Pageable pageable, Optional<User> user);

    //    get popular course
    List<Course> getPopuLarCourses();

    // get course by id
    Optional<CourseDto> getCourseDtoById(Integer courseId);
    Optional<Course> getCourseById(Integer courseId);

    String getSubject(Integer courseId);
}

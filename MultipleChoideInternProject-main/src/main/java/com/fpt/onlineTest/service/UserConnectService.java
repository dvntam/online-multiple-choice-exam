package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserConnectService {

    //User
    List<User> getAllUser();

    User login(String username, String password);

    Optional<User> getUserById(Integer userId);

    User createUser(User user);

    User checkUsername(String username);

    User checkEmail(String email);

    //Teacher
    List<Teacher> getAllTeacher();

    Teacher loginTeacher(String username, String password);

    Optional<Teacher> getTeacherById(Integer teacherId);

    Teacher createTeacher(Teacher teacher);

    Teacher checkUserNameForTeacher(String username);

    Teacher checkEmailForTeacher(String email);

    //Admin
    Admin loginAdmin(String username, String password);

}

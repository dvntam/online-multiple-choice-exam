package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.reponsitory.AdminRepository;
import com.fpt.onlineTest.reponsitory.TeacherRepository;
import com.fpt.onlineTest.reponsitory.UserReponsitory;
import com.fpt.onlineTest.service.UserConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserConnectServiceImpl implements UserConnectService {

    @Autowired
    UserReponsitory userReponsitory;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AdminRepository adminRepository;

    //User
    @Override
    public User login(String username, String password) {
        return userReponsitory.login(username, password);
    }

    @Override
    public List<User> getAllUser() {
        return userReponsitory.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return userReponsitory.findById(userId);
    }

    @Override
    public User createUser(User user) {
        userReponsitory.save(user);
        return user;
    }

    @Override
    public User checkUsername(String username) {
        return userReponsitory.findByUsername(username);
    }

    @Override
    public User checkEmail(String email) {
        return userReponsitory.findByEmail(email);
    }

    //Teacher
    @Override
    public Teacher loginTeacher(String username, String password) {
        return teacherRepository.login(username, password);
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getTeacherById(Integer teacherId) {
        return teacherRepository.findById(teacherId);
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public Teacher checkUserNameForTeacher(String username) {
        return teacherRepository.findByUsername2(username);
    }

    @Override
    public Teacher checkEmailForTeacher(String email) {
        return teacherRepository.findByEmail(email);
    }

    //Admin
    @Override
    public Admin loginAdmin(String username, String password) {
        return adminRepository.login(username, password);
    }


}

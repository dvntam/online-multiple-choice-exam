package com.fpt.onlineTest.service.impl;


import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.reponsitory.RoleRepository;
import com.fpt.onlineTest.reponsitory.TeacherRepository;
import com.fpt.onlineTest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;


    @Autowired
    RoleRepository roleRepository;


    @Override
    public boolean isUsernameTaken(String username) {
        Teacher existingTeacher = teacherRepository.findByUsername(username);
        return existingTeacher != null;
    }

    @Override
    public Teacher findByUsername(String username) {
        return teacherRepository.findByUsername(username);
    }

    @Override
    public <S extends Teacher> S saveAndFlush(S entity) {
        return teacherRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Teacher> S save(S entity) {
        return teacherRepository.save(entity);
    }

    @Override
    public Optional<Teacher> findById(Integer integer) {
        return teacherRepository.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        teacherRepository.deleteById(integer);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> findAll(Sort sort) {
        return teacherRepository.findAll(sort);
    }

    @Override
    public <S extends Teacher> List<S> findAll(Example<S> example) {
        return teacherRepository.findAll(example);
    }

    @Override
    public <S extends Teacher> List<S> findAll(Example<S> example, Sort sort) {
        return teacherRepository.findAll(example, sort);
    }
}

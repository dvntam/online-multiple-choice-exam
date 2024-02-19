package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Teacher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface TeacherService {


    boolean isUsernameTaken(String username);

    Teacher findByUsername(String username);

    <S extends Teacher> S saveAndFlush(S entity);

    <S extends Teacher> S save(S entity);

    Optional<Teacher> findById(Integer integer);

    void deleteById(Integer integer);

    List<Teacher> findAll();

    List<Teacher> findAll(Sort sort);

    <S extends Teacher> List<S> findAll(Example<S> example);

    <S extends Teacher> List<S> findAll(Example<S> example, Sort sort);
}

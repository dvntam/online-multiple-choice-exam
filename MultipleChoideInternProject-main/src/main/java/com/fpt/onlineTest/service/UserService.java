package com.fpt.onlineTest.service;

import com.fpt.onlineTest.dto.CourseDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.ResultExam;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.response.CustomResponse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface UserService {


    boolean isUsernameTaken(String username);

    User findByUsername(String username);

    List<User> findAll();

    List<User> findAll(Sort sort);

    List<User> findAllById(Iterable<Integer> integers);

    <S extends User> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends User> S saveAndFlush(S entity);

    <S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<User> entities);

    void deleteAllInBatch(Iterable<User> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    User getOne(Integer integer);

    @Deprecated
    User getById(Integer integer);

    User getReferenceById(Integer integer);

    <S extends User> List<S> findAll(Example<S> example);

    <S extends User> List<S> findAll(Example<S> example, Sort sort);

    Page<User> findAll(Pageable pageable);

    <S extends User> S save(S entity);

    Optional<User> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    boolean deleteById(Integer integer);

    void delete(User entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends User> entities);

    void deleteAll();

    <S extends User> Optional<S> findOne(Example<S> example);

    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends User> long count(Example<S> example);

    <S extends User> boolean exists(Example<S> example);

    <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
//------------------------
    // get userDto by id
    Optional<UserDto> getUserDtoById(Integer userId);

//    get finished exam of user
    Page<ResultExam> getUserFinishedExam(Integer userId,Pageable pageable);
//    update user
    User updateUser(Integer userId,User user);
    User updateUserImg(Integer userId,User user);
}

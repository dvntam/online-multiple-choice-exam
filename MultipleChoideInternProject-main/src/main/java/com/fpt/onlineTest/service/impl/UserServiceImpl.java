package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.dto.CourseDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.Blog;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.ResultExam;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.reponsitory.ExamRepository;
import com.fpt.onlineTest.reponsitory.RoleRepository;
import com.fpt.onlineTest.reponsitory.UserRepository;
import com.fpt.onlineTest.response.CustomResponse;
import com.fpt.onlineTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ExamRepository examRepository;

    @Override
    public boolean isUsernameTaken(String username) {
        User existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public List<User> findAllById(Iterable<Integer> integers) {
        return userRepository.findAllById(integers);
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return userRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        userRepository.flush();
    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return userRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return userRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<User> entities) {
        userRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {
        userRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        userRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        userRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public User getOne(Integer integer) {
        return userRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public User getById(Integer integer) {
        return userRepository.getById(integer);
    }

    @Override
    public User getReferenceById(Integer integer) {
        return userRepository.getReferenceById(integer);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return userRepository.findAll(example);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return userRepository.findAll(example, sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public <S extends User> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return userRepository.existsById(integer);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public boolean deleteById(Integer integer) {
        userRepository.deleteById(integer);
        return false;
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        userRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        userRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return userRepository.findOne(example);
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return userRepository.findAll(example, pageable);
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return userRepository.count(example);
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return userRepository.exists(example);
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return userRepository.findBy(example, queryFunction);
    }

    //----------------------------- hoviet -------------------------------//
    @Override
    public Optional<UserDto> getUserDtoById(Integer userId) {
        return userRepository.findById(userId).map(this::mapToDTO);
    }

    public Page<ResultExam> getUserFinishedExam(Integer userId, Pageable pageable) {
//        return examRepository.findFinishedExamOfUser(userId);
        return null;
    }

    @Override
    public User updateUser(Integer userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not found blog with id: " + userId));
        // Cập nhật các thuộc tính của đối tượng Blog từ đối tượng blog được truyền vào
        if (user.getFullName() != null) {
            existingUser.setFullName(user.getFullName());
        }
        if (user.getAddress() != null) {
            existingUser.setAddress(user.getAddress());
        }
        if (user.getPhone() != null) {
            existingUser.setPhone(user.getPhone());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getUserPass() != null) {
            existingUser.setUserPass(user.getUserPass());
        }
        return userRepository.save(existingUser);
    }

    @Override
    public User updateUserImg(Integer userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not found blog with id: " + userId));
        if (user.getImageUser() != null) {
            existingUser.setImageUser(user.getImageUser());
        }
        return userRepository.save(existingUser);
    }


    private UserDto mapToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(userDto.getEmail());
        userDto.setPhone(userDto.getPhone());
        userDto.setImageUser(user.getImageUser());
        return userDto;
    }
}

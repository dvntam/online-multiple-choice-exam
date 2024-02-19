package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Address;
import com.fpt.onlineTest.model.Person;
import com.fpt.onlineTest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserReponsitory extends JpaRepository< User,Integer> {
    @Query("select u from User u where u.userId in :userId")
    Page<User> findCourseSUsers(@Param("userId") List<Integer> userId, Pageable pageable);

    @Query(value = "SELECT * FROM users WHERE username = ? AND user_pass = ?", nativeQuery = true)
    User login(String userName, String password);

    @Query(value = "SELECT * FROM users WHERE username = ?", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE email = ?", nativeQuery = true)
    User findByEmail(String email);

}


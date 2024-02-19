package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    Teacher findByUsername(String username);

    @Query(value = "SELECT * FROM teachers WHERE username = ? AND user_pass = ?", nativeQuery = true)
    Teacher login(String userName, String password);

    @Query(value = "SELECT * FROM teachers WHERE username = ?", nativeQuery = true)
    Teacher findByUsername2(String username);

    @Query(value = "SELECT * FROM teachers WHERE email = ?", nativeQuery = true)
    Teacher findByEmail(String email);
}

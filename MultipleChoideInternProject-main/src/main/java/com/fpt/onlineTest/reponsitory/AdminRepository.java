package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String username);

    @Query(value = "SELECT * FROM admin WHERE username = ? AND user_pass = ?", nativeQuery = true)
    Admin login(String userName, String password);

}

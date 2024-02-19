package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.ResultExam;
import com.fpt.onlineTest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

//  -----  hoviet -----  ///


}

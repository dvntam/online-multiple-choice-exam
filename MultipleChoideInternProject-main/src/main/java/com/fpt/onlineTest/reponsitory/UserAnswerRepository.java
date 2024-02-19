package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.UserAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswers, Integer> {
    @Query(value = "SELECT * FROM user_answers WHERE user_id = ?", nativeQuery = true)
    List<UserAnswers> findUserAnswerByUserId(Integer userId);
}

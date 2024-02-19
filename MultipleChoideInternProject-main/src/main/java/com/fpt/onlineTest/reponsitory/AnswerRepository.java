package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    @Query(value = "SELECT * FROM Answers WHERE status = ?", nativeQuery = true)
    List<Answer> findAnswerByStatus(String status);
}

package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Questions;
import com.fpt.onlineTest.model.ResultQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultQuestionRepository extends JpaRepository<ResultQuestion, Integer> {
    @Query(value = "SELECT * FROM resultQuestion WHERE answerId = ?", nativeQuery = true)
    List<ResultQuestion> findResultByAnswerId(Integer answerId);
}

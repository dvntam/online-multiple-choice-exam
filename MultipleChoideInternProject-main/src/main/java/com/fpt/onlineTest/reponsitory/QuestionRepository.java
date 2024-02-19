package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions, Integer> {
    @Query(value = "SELECT * FROM Questions WHERE level = ?", nativeQuery = true)
    List<Questions> findQuestionByLevel(String level);

    @Query(value = "SELECT * FROM Questions WHERE subject = ?", nativeQuery = true)
    List<Questions> findQuestionBySubject(String subject);
    // get question based on number question and level
    @Query(value = "SELECT q.questionId FROM Questions q WHERE q.subject =:subject and q.level =:level ORDER BY NEWID()")
    List<Integer> getRandomQuestionId(@Param("subject") String subject, @Param("level") String level);
}

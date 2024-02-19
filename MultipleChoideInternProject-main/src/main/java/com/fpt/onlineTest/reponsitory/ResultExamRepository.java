package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.ResultExam;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultExamRepository extends JpaRepository<ResultExam, Integer> {
    @Query(value = "SELECT * FROM result_exams WHERE user_id = ?", nativeQuery = true)
    List<ResultExam> findResultExamByUserId(Integer userId);

    @Query(value = "SELECT * FROM result_exams WHERE exam_id = ?", nativeQuery = true)
    List<ResultExam> findResultExamByExamId(Integer examId);

    @Query(value = "SELECT * FROM result_exams WHERE user_id = ? AND exam_id = ?", nativeQuery = true)
    List<ResultExam> findResultExamByUserIdAndExamId(Integer userId, Integer examId);

    @Query(value = "SELECT * FROM result_exams ORDER BY point ASC", nativeQuery = true)
    List<ResultExam> findResultExamByPointASC();

    @Query(value = "SELECT * FROM result_exams ORDER BY point DESC", nativeQuery = true)
    List<ResultExam> findResultExamByPointDESC();
}


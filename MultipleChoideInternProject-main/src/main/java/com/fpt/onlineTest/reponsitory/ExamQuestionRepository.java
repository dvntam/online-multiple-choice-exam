package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.ExamQuestion;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Integer> {
//    @Query("select eq,qa from ExamQuestion eq join Answer qa on eq.question.questionId = qa.question.questionId where eq.exam.examId=:id")
//    @Query("select q from Questions q " +
//            "join ExamQuestion eq on eq.question.questionId = q.questionId " +
////            "join Answer a on a.question.questionId = q.questionId " +
//            "where eq.exam.examId=:id")
//    @Query("SELECT eq FROM ExamQuestion eq " +
//        "left join Questions q on eq.question.questionId = q.questionId " +
//        "left join Answer a on q.answer.size = a.answerId " +
//        "WHERE eq.exam.examId = :id")
    @Query(value = "SELECT * FROM exam_question  \n" +
            "left join questions  on exam_question.question_id = questions.question_id\n" +
            "left join answers on questions.question_id = answers.answer_id\n" +
            "WHERE exam_question.exam_id =?", nativeQuery = true)
    Page<ExamQuestion> findExamQuestionByExamId(@Param("id") Integer id, Pageable pageable);

    @Transactional
    @Modifying
    @Query("delete from ExamQuestion eq where eq.exam.examId=:examId")
    void deleteExamAllQuestion(@Param("examId") Integer examId);

    @Query("select eq from ExamQuestion eq where eq.exam.examId=:examId and eq.question.questionId=:questionId")
    ExamQuestion findQuestionExist(@Param("examId") Integer examId,@Param("questionId")  Integer questionId);
}

package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.model.ExamQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ExamQuestionService {
    // create
    List<ExamQuestion> createExamTest(Integer nums, Exam exam, String subject, String level);

    //    create random exam have random level with {subject} and {nums} questions
    List<ExamQuestion> createExamTestWithRandomQuestion(Integer nums, Integer examId, String subject);

    //    add selected questions to exam test
    List<ExamQuestion> addMultipleQuestions(List<ExamQuestion> examQuestions);

    //    get exam test question
    Page<ExamQuestion> getExamTestQuestions(Integer id, Pageable pageable);

    //  delete 1 question from exam test
    void deleteQuestionById(Integer questionId);

    //    delete all question from exam test
    void deleteExamAllQuestion(Integer examId);

    // delete list of question
    void deleteListOfQuestion(List<Integer> listId);

}

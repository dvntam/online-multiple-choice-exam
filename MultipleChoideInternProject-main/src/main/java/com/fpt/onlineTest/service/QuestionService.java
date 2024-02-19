package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Questions;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    //Get all questions
    List<Questions> getAllQuestion();

    //Get question by id
    Optional<Questions> getQuestionById(Integer questionId);

    //Create question
    void newQuestion(List<Questions> questions);

    //Delete question
    void deleteQuestion(Integer questionId);

    //Get question by level
    List<Questions> getQuestionByLevel(String level);

    //Get question by level
    List<Questions> getQuestionBySubject(String subject);

    //Update question
    Questions updateQuestion(Questions question);
}

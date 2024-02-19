package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.ResultQuestion;

import java.util.List;

public interface ResultQuestionService {
    //Get all result
    List<ResultQuestion> getAllResult();

    //Get result by answerId
    List<ResultQuestion> getResultByAnswerId(Integer answerId);

    //Create result question
    void createResultQuestion(List<ResultQuestion> resultQuestions);
}

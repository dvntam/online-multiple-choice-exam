package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.ResultQuestion;
import com.fpt.onlineTest.reponsitory.ResultQuestionRepository;
import com.fpt.onlineTest.service.ResultQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultQuestionServiceImpl implements ResultQuestionService {

    @Autowired
    ResultQuestionRepository resultQuestionRepository;

    @Override
    public List<ResultQuestion> getAllResult() {
        return resultQuestionRepository.findAll();
    }

    @Override
    public List<ResultQuestion> getResultByAnswerId(Integer answerId) {
        return resultQuestionRepository.findResultByAnswerId(answerId);
    }

    @Override
    public void createResultQuestion(List<ResultQuestion> resultQuestions) {
        resultQuestionRepository.saveAll(resultQuestions);
    }
}

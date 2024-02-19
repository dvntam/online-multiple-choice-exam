package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Answer;
import com.fpt.onlineTest.model.Questions;
import com.fpt.onlineTest.reponsitory.QuestionRepository;
import com.fpt.onlineTest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public List<Questions> getAllQuestion() {
        return questionRepository.findAll();
    }
    @Override
    public Optional<Questions> getQuestionById(Integer questionId) {
        return questionRepository.findById(questionId);
    }
    @Override
    public void newQuestion(List<Questions> questions) {
        questionRepository.saveAll(questions);
    }
    @Override
    public void deleteQuestion(Integer questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public List<Questions> getQuestionByLevel(String level) {
        return questionRepository.findQuestionByLevel(level);
    }

    @Override
    public List<Questions> getQuestionBySubject(String subject) {
        return questionRepository.findQuestionBySubject(subject);
    }

    @Override
    public Questions updateQuestion(Questions question) {
        return questionRepository.save(question);
    }

}

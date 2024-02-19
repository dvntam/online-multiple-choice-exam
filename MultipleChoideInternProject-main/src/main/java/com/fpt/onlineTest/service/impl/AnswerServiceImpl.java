package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Answer;
import com.fpt.onlineTest.reponsitory.AnswerRepository;
import com.fpt.onlineTest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> getAllAnswer() {
        return answerRepository.findAll();
    }

    @Override
    public Optional<Answer> getAnswerById(Integer answerId) {
        return answerRepository.findById(answerId);
    }

    @Override
    public List<Answer> getAnswerByStatus(String status) {
        return answerRepository.findAnswerByStatus(status);
    }

    @Override
    public Answer newAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Integer answerId) {
        answerRepository.deleteById(answerId);
    }

    @Override
    public Answer updateAnswer(Answer answer) {
        return answerRepository.save(answer);
    }
}

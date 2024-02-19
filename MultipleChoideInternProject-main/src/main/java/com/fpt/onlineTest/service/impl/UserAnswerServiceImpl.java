package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Answer;
import com.fpt.onlineTest.model.UserAnswers;
import com.fpt.onlineTest.reponsitory.AnswerRepository;
import com.fpt.onlineTest.reponsitory.UserAnswerRepository;
import com.fpt.onlineTest.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    @Autowired
    UserAnswerRepository userAnswerRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public List<UserAnswers> getAllUserAnswers() {
        return userAnswerRepository.findAll();
    }

    @Override
    public List<UserAnswers> getUserAnswerByUserId(Integer userId) {
        return userAnswerRepository.findUserAnswerByUserId(userId);
    }

    @Override
    public UserAnswers createUserAnswer(List<UserAnswers> userAnswers) {
        for (UserAnswers userAnswer : userAnswers) {
            String status = "true";
            List<Answer> answers = answerRepository.findAnswerByStatus(status);
            boolean isCorrect = false;
            for (Answer answer : answers) {
                if (answer.getAnswerId().equals(userAnswer.getAnswer().getAnswerId())) {
                    isCorrect = true;
                    break;
                }
            }

            userAnswer.setStatus (isCorrect ? "true" : "false");
            userAnswerRepository.save(userAnswer);
        }
        return null;
    }

    @Override
    public void deleteUserAnswer(Integer userAnswerId) {
        userAnswerRepository.deleteById(userAnswerId);
    }
}

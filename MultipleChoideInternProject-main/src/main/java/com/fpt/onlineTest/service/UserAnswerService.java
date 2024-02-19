package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.UserAnswers;

import java.util.List;

public interface UserAnswerService {
    //Get all UserAnswer
    List<UserAnswers> getAllUserAnswers();

    //Get UserAnswer by userId
    List<UserAnswers> getUserAnswerByUserId(Integer userId);

    //Add new UserAnswer
    UserAnswers createUserAnswer(List<UserAnswers> userAnswers);

    //Delete userAnswer
    void deleteUserAnswer(Integer userAnswerId);
}

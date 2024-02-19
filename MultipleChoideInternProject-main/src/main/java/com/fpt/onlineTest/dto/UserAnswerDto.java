package com.fpt.onlineTest.dto;


import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.model.UserAnswers;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserAnswerDto {
    private Exam exam;
    private User user;
    private List<AnswerDto> answerDtos;
}

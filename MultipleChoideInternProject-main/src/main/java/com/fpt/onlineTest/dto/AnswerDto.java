package com.fpt.onlineTest.dto;

import com.fpt.onlineTest.model.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDto {
    private Answer answer;
    private String status;
}

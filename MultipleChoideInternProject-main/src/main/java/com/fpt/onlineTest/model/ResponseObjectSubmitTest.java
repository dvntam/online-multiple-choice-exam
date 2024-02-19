package com.fpt.onlineTest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseObjectSubmitTest {
    private String status;
    private String message;
    private Object userAnswer;
    private Object resultExam;
}

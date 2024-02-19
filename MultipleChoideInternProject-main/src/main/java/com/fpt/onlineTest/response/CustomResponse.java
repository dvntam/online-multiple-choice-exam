package com.fpt.onlineTest.response;

import com.fpt.onlineTest.model.ResultExam;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomResponse {
    private List<ResultExam> resultExams;
}

package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.ResultExam;
import com.fpt.onlineTest.model.ResultExamStats;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ResultExamService {
    //Get all resultExam
    List<ResultExam> getAllResultExam();

    //Get resultExam by id
    Optional<ResultExam> getResultExamById(Integer resultExamId);

    //Get resultExam by userId
    List<ResultExam> getResultExamByUserId(Integer userId);

    //Get resultExam by examId
    List<ResultExam> getResultExamByExamId(Integer examId);

    //Get resultExam by userId
    List<ResultExam> getResultExamByUserIdAndExamId(Integer userId, Integer examId);

    //Get resultExam by point ASC
    List<ResultExam> getResultExamByPointASC();

    //Get resultExam by point DESC
    List<ResultExam> getResultExamByPointDESC();

    //Get stats resultExam
    ResultExamStats getStats();

    //Get stats resultExam by examID
    ResultExamStats getStatsByExamId(Integer examId);

    //Get stats resultExam by userID
    ResultExamStats getStatsByUserId(Integer userId);

    //Create resultExam
    ResultExam createResultExam(ResultExam resultExam);

    //Delete resultExam
    void deleteResultExam(Integer resultExamId);

    //export file to excel
    ByteArrayInputStream exportFileToExcel();
}

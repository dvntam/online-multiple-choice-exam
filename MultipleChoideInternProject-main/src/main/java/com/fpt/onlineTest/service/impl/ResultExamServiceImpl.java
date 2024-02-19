package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.helper.ExcelHelper;
import com.fpt.onlineTest.model.ResultExam;
import com.fpt.onlineTest.model.ResultExamStats;
import com.fpt.onlineTest.reponsitory.ResultExamRepository;
import com.fpt.onlineTest.service.ResultExamService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultExamServiceImpl implements ResultExamService {
    @Autowired
    ResultExamRepository resultExamRepository;
    @Override
    public List<ResultExam> getAllResultExam() {
        return resultExamRepository.findAll();
    }

    @Override
    public Optional<ResultExam> getResultExamById(Integer resultExamId) {
        return resultExamRepository.findById(resultExamId);
    }

    @Override
    public List<ResultExam> getResultExamByUserId(Integer userId) {
        return resultExamRepository.findResultExamByUserId(userId);
    }

    @Override
    public List<ResultExam> getResultExamByExamId(Integer examId) {
        return resultExamRepository.findResultExamByExamId(examId);
    }

    @Override
    public List<ResultExam> getResultExamByUserIdAndExamId(Integer userId, Integer examId) {
        return resultExamRepository.findResultExamByUserIdAndExamId(userId, examId);
    }

    @Override
    public List<ResultExam> getResultExamByPointASC() {
        return resultExamRepository.findResultExamByPointASC();
    }

    @Override
    public List<ResultExam> getResultExamByPointDESC() {
        return resultExamRepository.findResultExamByPointDESC();
    }

    @Override
    public ResultExamStats getStats() {
        List<ResultExam> listResultExam = resultExamRepository.findResultExamByPointASC();
        List<ResultExam> passedCount = new ArrayList<>();
        List<ResultExam> failedCount = new ArrayList<>();
        for(ResultExam resultExam : listResultExam) {
            if(resultExam.getPoint() > 60) {
                passedCount.add(resultExam);
            }else {
                failedCount.add(resultExam);
            }
        }
        return new ResultExamStats(passedCount, failedCount);
    }

    @Override
    public ResultExamStats getStatsByExamId(Integer examId) {
        List<ResultExam> listResultExam = resultExamRepository.findResultExamByExamId(examId);
        List<ResultExam> passedCount = new ArrayList<>();
        List<ResultExam> failedCount = new ArrayList<>();
        for(ResultExam resultExam : listResultExam) {
            if(resultExam.getPoint() > 60) {
                passedCount.add(resultExam);
            }else {
                failedCount.add(resultExam);
            }
        }
        return new ResultExamStats(passedCount, failedCount);
    }

    @Override
    public ResultExamStats getStatsByUserId(Integer userId) {
        List<ResultExam> listResultExam = resultExamRepository.findResultExamByUserId(userId);
        List<ResultExam> passedCount = new ArrayList<>();
        List<ResultExam> failedCount = new ArrayList<>();
        for(ResultExam resultExam : listResultExam) {
            if(resultExam.getPoint() > 60) {
                passedCount.add(resultExam);
            }else {
                failedCount.add(resultExam);
            }
        }
        return new ResultExamStats(passedCount, failedCount);
    }

    @Override
    public ResultExam createResultExam(ResultExam resultExam) {
        return resultExamRepository.save(resultExam);
    }

    @Override
    public void deleteResultExam(Integer resultExamId) {
        resultExamRepository.deleteById(resultExamId);
    }

    @Override
    public ByteArrayInputStream exportFileToExcel()  {
        List<ResultExam> resultExams = resultExamRepository.findAll();
        ByteArrayInputStream in = ExcelHelper.resultExamToExcel(resultExams);
        return in;
    }
}

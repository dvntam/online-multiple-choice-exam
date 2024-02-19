package com.fpt.onlineTest.model;

import java.util.List;

public class ResultExamStats {
    private List<ResultExam> passedCount;
    private List<ResultExam> failedCount;

    public ResultExamStats(List<ResultExam> passedCount, List<ResultExam> failedCount) {
        this.passedCount = passedCount;
        this.failedCount = failedCount;
    }

    public List<ResultExam> getPassedCount() {
        return passedCount;
    }

    public void setPassedCount(List<ResultExam> passedCount) {
        this.passedCount = passedCount;
    }

    public List<ResultExam> getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(List<ResultExam> failedCount) {
        this.failedCount = failedCount;
    }
}

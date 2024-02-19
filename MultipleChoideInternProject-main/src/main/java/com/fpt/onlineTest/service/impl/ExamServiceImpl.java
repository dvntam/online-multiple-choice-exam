package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.helper.QuestionExcelHelper;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.model.ExamQuestion;
import com.fpt.onlineTest.model.Questions;
import com.fpt.onlineTest.reponsitory.CourseRepository;
import com.fpt.onlineTest.reponsitory.ExamRepository;
import com.fpt.onlineTest.reponsitory.UserRepository;
import com.fpt.onlineTest.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Exam createExam(Exam exam) {
        Course course = courseRepository.findById(exam.getCourses().getCourseId()).orElse(null);
        if (course == null)
            return null;
        else
            exam.setCourses(course);
        return examRepository.save(exam);
    }

    @Override
    public Exam updateExam(Integer examId, Exam exam) {
        Exam exitingExam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("exam not found! id: " + examId));
        if (exitingExam.getNumQuestion() != null)
            exitingExam.setNumQuestion(exam.getNumQuestion());

        if(exitingExam.getExamName()!=null){
            exitingExam.setExamName(exam.getExamName());
        }

        if (exitingExam.getDuration() != null)
            exitingExam.setDuration(exam.getDuration());

        return examRepository.save(exitingExam);
    }

    @Override
    public Optional<Exam> getExamById(Integer examId) {
        return examRepository.findById(examId);
    }

    @Override
    public List<Exam> getExamsByCourseId(Integer courseId) {
        return examRepository.findExamByCourseId(courseId);
    }

    @Override
    public void deleteExamBydId(Integer examId) {
        examRepository.deleteById(examId);
    }

    @Override
    public void deleteExamByCourseId(Integer courseId) {
        examRepository.deleteExamByCourseId(courseId);
    }

    @Override
    public Page<Exam> getIncomingExamByUserId(Integer userId, Pageable pageable) {
        Page<Exam> incomingExamOfUserList = examRepository.findIncomingExamOfUser(userId, pageable);
        Page<Exam> finishedExamOfUserList = examRepository.findFinishedExamOfUser(userId,pageable);
        Page<Exam> bandedExamOfUserList = examRepository.findBandedExamOfUser(userId, pageable);

        List<Exam> userExamList = new ArrayList<>();
        List<Exam> incomingUserExamList = new ArrayList<>();
        // add finished exam to user exam list
        for (Exam finishedExamOfUser: finishedExamOfUserList) {
                userExamList.add(finishedExamOfUser);
        }
        // add banded exam to user exam list
        for (Exam bandedExamOfUser: bandedExamOfUserList) {
                userExamList.add(bandedExamOfUser);
        }
        // add incoming to user exam list
        for (Exam incomingExamOfUser: incomingExamOfUserList) {
            if (!userExamList.contains(incomingExamOfUser)){
                incomingUserExamList.add(incomingExamOfUser);
            }
        }

        return new PageImpl<>(incomingUserExamList, pageable, userExamList.size());
    }

    @Override
    public Page<Exam> getFinishedExamByUserId(Integer userId, Pageable pageable){
        return examRepository.findFinishedExamOfUser(userId,pageable);
    }

    @Override
    public Page<Exam> getBandedExamByUserId(Integer userId, Pageable pageable){
        return examRepository.findBandedExamOfUser(userId,pageable);
    }
    @Override
    public ByteArrayInputStream exportFileToExcel(Integer id) {
        Exam exam = examRepository.findById(id).orElseGet(null);
        List<ExamQuestion> EQuestions = exam.getExamQuestions();
        List<Questions> questions = new ArrayList<>();

        for (ExamQuestion examQuestion: EQuestions) {
            questions.add(examQuestion.getQuestion());
        }
        ByteArrayInputStream in = QuestionExcelHelper.QuestionToExcel(questions);
        return in;
    }
}

package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.dto.AnswerDto;
import com.fpt.onlineTest.dto.UserAnswerDto;
import com.fpt.onlineTest.model.ResponseObject;
import com.fpt.onlineTest.model.ResponseObjectSubmitTest;
import com.fpt.onlineTest.model.ResultExam;
import com.fpt.onlineTest.model.UserAnswers;
import com.fpt.onlineTest.service.ResultExamService;
import com.fpt.onlineTest.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user-answers")
public class UserAnswerController {
    @Autowired
    UserAnswerService userAnswerService;

    @Autowired
    ResultExamService resultExamService;

    @GetMapping("")
    public ResponseEntity<List<UserAnswers>> getAllUserAnswer() {
        try{
            return new ResponseEntity<>(userAnswerService.getAllUserAnswers(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<List<UserAnswers>> getUserAnswerByUserId(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(userAnswerService.getUserAnswerByUserId(id), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObjectSubmitTest> newUserAnswer(@RequestBody UserAnswerDto newUserAnswerDto) {
        try {
            List<UserAnswers> newUserAnswers = new ArrayList<>();
            for(AnswerDto answerDto : newUserAnswerDto.getAnswerDtos()) {
                UserAnswers userAnswer = new UserAnswers();
                userAnswer.setAnswer(answerDto.getAnswer());
                userAnswer.setUser(newUserAnswerDto.getUser());
                userAnswer.setStatus(answerDto.getStatus());
                newUserAnswers.add(userAnswer);
            }
            userAnswerService.createUserAnswer(newUserAnswers);
            List<UserAnswers> userAnswersTrue = new ArrayList<>();
            for(UserAnswers userAnswer : newUserAnswers) {
                if (userAnswer.getStatus() == "true") {
                    userAnswersTrue.add(userAnswer);
                }
            }
            ResultExam newResultExam = new ResultExam();
            newResultExam.setUser(newUserAnswerDto.getUser());
            newResultExam.setExam(newUserAnswerDto.getExam());
            Double point = ((double) userAnswersTrue.size() / newUserAnswers.size()) * 100;
            newResultExam.setPoint(point);
            resultExamService.createResultExam(newResultExam);
            return new ResponseEntity<>(new ResponseObjectSubmitTest("success", "Submit test successfully!!", newUserAnswers, newResultExam),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseObjectSubmitTest("failed", e.getMessage(), "", "") ,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserAnswer(@PathVariable Integer id) {
        try {
            userAnswerService.deleteUserAnswer(id);
            new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Questions;
import com.fpt.onlineTest.model.ResultQuestion;
import com.fpt.onlineTest.model.UserAnswers;
import com.fpt.onlineTest.service.ResultQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/results")
public class ResultQuestionController {
    @Autowired
    ResultQuestionService resultQuestionService;

    @GetMapping("")
    public ResponseEntity<List<ResultQuestion>> getAllResult() {
        try {
            return new ResponseEntity<>(resultQuestionService.getAllResult(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{answerId}")
    public ResponseEntity<List<ResultQuestion>> getResultByAnswerId(@PathVariable Integer answerId) {
        try {
            return new ResponseEntity<>(resultQuestionService.getResultByAnswerId(answerId), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResultQuestion> newResultQuestion(@RequestBody List<ResultQuestion> resultQuestions) {
        try {
            resultQuestionService.createResultQuestion(resultQuestions);
            return new ResponseEntity<>( HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

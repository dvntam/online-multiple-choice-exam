package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Answer;
import com.fpt.onlineTest.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/answers")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping("")
    public ResponseEntity<List<Answer>> getAllAnswer() {
        try {
            return new ResponseEntity<>(answerService.getAllAnswer(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer newAnswer) {
        try{
            return new ResponseEntity<>(answerService.newAnswer(newAnswer), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Answer>> getAnswerByStatus(@PathVariable String status) {
        try {
            return new ResponseEntity<>(answerService.getAnswerByStatus(status), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnswer(@PathVariable Integer id){
        try {
            answerService.deleteAnswer(id);
            new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Integer id, @RequestBody Answer newAnswer){
        System.out.println(id);
        Answer answer = answerService.getAnswerById(id)
                .map(ans -> {
                    ans.setContentAnswer(newAnswer.getContentAnswer());
                    ans.setQuestion(newAnswer.getQuestion());
                    return answerService.updateAnswer(ans);
                }).orElseGet(() -> {
                    newAnswer.setAnswerId(id);
                    return answerService.updateAnswer(newAnswer);
                });
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

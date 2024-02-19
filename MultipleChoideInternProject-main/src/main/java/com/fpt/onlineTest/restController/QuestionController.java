package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Questions;
import com.fpt.onlineTest.model.ResponseObject;
import com.fpt.onlineTest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<List<Questions>> getAllQuestion() {
        try {
            return new ResponseEntity<>(questionService.getAllQuestion(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Questions>> getQuestionById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> createQuestion(@RequestBody List<Questions> newQuestions) {
        try {
            questionService.newQuestion(newQuestions);
            return new ResponseEntity<>(new ResponseObject("success", "successfully", ""), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseObject("failed", e.getMessage(), ""), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        try {
            questionService.deleteQuestion(id);
            new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<Questions>> getQuestionByLevel(@PathVariable String level) {
        try {
            System.out.println(level);
            return new ResponseEntity<>(questionService.getQuestionByLevel(level), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity<List<Questions>> getQuestionBySubject(@PathVariable String subject) {
        try {
            System.out.println(subject);
            return new ResponseEntity<>(questionService.getQuestionBySubject(subject), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Questions> updateQuestion(@PathVariable Integer id, @RequestBody Questions newQuestion) {
        Questions question = questionService.getQuestionById(id)
                .map(ques -> {
                    ques.setContentQuestion(newQuestion.getContentQuestion());
                    ques.setLevel(newQuestion.getLevel());
                    ques.setSubject(newQuestion.getSubject());
                    return questionService.updateQuestion(ques);
                }).orElseGet(() -> {
                    newQuestion.setQuestionId(id);
                    return questionService.updateQuestion(newQuestion);
                });
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

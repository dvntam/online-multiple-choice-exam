package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.ResponseObject;
import com.fpt.onlineTest.model.ResultExam;
import com.fpt.onlineTest.model.ResultExamStats;
import com.fpt.onlineTest.service.ResultExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/result-exam")
public class ResultExamController {
    @Autowired
    ResultExamService resultExamService;
    @GetMapping("")
    public ResponseEntity<List<ResultExam>> getAllResultExam(){
        try{
            return new ResponseEntity<>(resultExamService.getAllResultExam(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ResultExam>> getResultExamByUserId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(resultExamService.getResultExamByUserId(id), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/exam/{id}")
    public ResponseEntity<List<ResultExam>> getResultExamByExamId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(resultExamService.getResultExamByExamId(id), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user-and-exam/{id1}/{id2}")
    public ResponseEntity<List<ResultExam>> getResultExamByUserIdAndExamId(@PathVariable Integer id1, @PathVariable Integer id2) {
        try {
            return new ResponseEntity<>(resultExamService.getResultExamByUserIdAndExamId(id1, id2), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/point-asc")
    public ResponseEntity<List<ResultExam>> getResultExamByPointASC(){
        try{
            return new ResponseEntity<>(resultExamService.getResultExamByPointASC(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/point-desc")
    public ResponseEntity<List<ResultExam>> getResultExamByPointDESC(){
        try{
            return new ResponseEntity<>(resultExamService.getResultExamByPointDESC(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<ResultExamStats> getStats() {
        try{
            return new ResponseEntity<>(resultExamService.getStats(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stats/exam/{id}")
    public ResponseEntity<ResultExamStats> getStatsByExamId(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(resultExamService.getStatsByExamId(id), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stats/user/{id}")
    public ResponseEntity<ResultExamStats> getStatsByUserId(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(resultExamService.getStatsByUserId(id), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResultExam> createResultExam(@RequestBody ResultExam resultExam) {
        try{
            return new ResponseEntity<>(resultExamService.createResultExam(resultExam), HttpStatus.OK) ;
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteResultExam(@PathVariable Integer id) {
        try{
            resultExamService.deleteResultExam(id);
            new ResponseEntity<>(new ResponseObject("success","Delete resultExam successfully!!",""),HttpStatus.OK);
        }catch (Exception e) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/export-file-excel")
    public ResponseEntity<InputStreamResource> exportFileToExcel()  {
        String filename = "resultExam.xlsx";
        InputStreamResource file = new InputStreamResource(resultExamService.exportFileToExcel());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}

package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Exam;
import com.fpt.onlineTest.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping("/course/exam/add")
    public ResponseEntity<Object> createExam(@RequestBody Exam newExam){
        try {
            return new ResponseEntity<>(examService.createExam(newExam), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred: " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/course/exam/update/{id}")
    public ResponseEntity<Object> updateExam(@PathVariable Integer id,@RequestBody Exam exam){
        try {
            return new ResponseEntity<>(examService.updateExam(id,exam), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred: " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/exam/{id}")
    public ResponseEntity<Object> getExamById(@PathVariable Integer id){
        try {
            return new ResponseEntity<>(examService.getExamById(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred: " + e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/course={courseId}/exams")
    public ResponseEntity<Object> getExamByCourseId(@PathVariable Integer courseId){
        try {

            return new ResponseEntity<>(examService.getExamsByCourseId(courseId),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("An error occurred: " + e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/course/exam/delete/{id}")
    public ResponseEntity<HttpStatus> deleteExamById(@PathVariable Integer id){
        try {
            examService.deleteExamBydId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/course={id}/exams/delete")
    public ResponseEntity<HttpStatus> deleteExamByCourseId(@PathVariable Integer id){
        try {
            examService.deleteExamByCourseId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user/{id}/incoming-exams")
    public ResponseEntity<Object> getIncomingExamsOfUser(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Exam> resultExamPage = examService.getIncomingExamByUserId(id, pageable);

            if (resultExamPage.isEmpty()) {
                // Trả về thông báo nếu không có kết quả
                return new ResponseEntity<>("No exams found for the user.", HttpStatus.NOT_FOUND);
            } else {
                // Trả về danh sách kết quả
                return new ResponseEntity<>(resultExamPage, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user/{id}/finished-exams")
    public ResponseEntity<Object> getFinishedExamsOfUser(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Exam> resultExamPage = examService.getFinishedExamByUserId(id, pageable);

            if (resultExamPage.isEmpty()) {
                // Trả về thông báo nếu không có kết quả
                return new ResponseEntity<>("No finished exams found for the user.", HttpStatus.NOT_FOUND);
            } else {
                // Trả về danh sách kết quả
                return new ResponseEntity<>(resultExamPage, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("user/{id}/banded-exams")
    public ResponseEntity<Object> getBandedExamsOfUser(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Exam> resultExamPage = examService.getBandedExamByUserId(id, pageable);

            if (resultExamPage.isEmpty()) {
                // Trả về thông báo nếu không có kết quả
                return new ResponseEntity<>("No banded exams found for the user.", HttpStatus.NOT_FOUND);
            } else {
                // Trả về danh sách kết quả
                return new ResponseEntity<>(resultExamPage, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/export-file-excel/{id}")
    public ResponseEntity<InputStreamResource> exportFileToExcel(@PathVariable Integer id)  {
        String filename = "question.xlsx";
        InputStreamResource file = new InputStreamResource(examService.exportFileToExcel(id));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}

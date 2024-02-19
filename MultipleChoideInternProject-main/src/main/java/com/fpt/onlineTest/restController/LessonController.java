package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.dto.LessonDto;
import com.fpt.onlineTest.model.Lesson;
import com.fpt.onlineTest.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class LessonController {
    @Autowired
    LessonService lessonService;

    //    create lesson
    @PostMapping("/course/chapter/lesson/add")
    public ResponseEntity<List<Lesson>> newLesson(@RequestBody List<Lesson> newLesson) {
        try {
            return new ResponseEntity<>(lessonService.createLesson(newLesson), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    get chapter's lesson
    @GetMapping("/course/chapter-id={chapterId}/lessons")
    public ResponseEntity<List<LessonDto>> getChapterSLesson(@PathVariable Integer chapterId) {
        try {
            return new ResponseEntity<>(lessonService.getLessonByChapterId(chapterId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    update lesson
    @PutMapping("/course/chapter/lesson/update/{lessonId}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Integer lessonId, @RequestBody Lesson lesson) {
        try {
            return new ResponseEntity<>(lessonService.updateLesson(lessonId, lesson), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    delete lesson by id
    @DeleteMapping("/course/chapter/lesson/delete/{lessonId}")
    public ResponseEntity<HttpStatus> deleteLessonById(@PathVariable Integer lessonId) {
        try {
            lessonService.deleteLessonById(lessonId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

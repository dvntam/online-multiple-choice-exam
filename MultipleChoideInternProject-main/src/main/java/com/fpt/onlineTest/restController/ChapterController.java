package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.Chapter;
import com.fpt.onlineTest.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //    create chapter
    @PostMapping("/course/chapters/create")
    public ResponseEntity<List<Chapter>> createNewChapter(@RequestBody List<Chapter> newChapter) {
        try {
            return new ResponseEntity<>(chapterService.createChapter(newChapter), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    get course's chapters
    @GetMapping("/course/id={courseId}/chapters")
    public ResponseEntity<List<Chapter>> getCourseAllChapters(@PathVariable Integer courseId) {
        try {
            return new ResponseEntity<>(chapterService.getCourseChapters(courseId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete by chapter id
    @DeleteMapping("/course/chapters/delete-chapter/{id}")
    public ResponseEntity<HttpStatus> deleteChapter(@PathVariable Integer id) {
        try {
            chapterService.deleteChapterById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update chapter
    @PutMapping("/course/update/chapter/id={chapterId}")
    public ResponseEntity<Chapter> updateChapter(@PathVariable Integer chapterId,@RequestBody Chapter chapter){
        try {
            return new ResponseEntity<>(chapterService.updateChapter(chapterId,chapter),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete course chapter
    @DeleteMapping("/course/chapter/delete/course-id={courseId}")
    public ResponseEntity<HttpStatus> deleteCourseSChapter(@PathVariable Integer courseId){
        try {
            chapterService.deleteCourseSChapter(courseId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

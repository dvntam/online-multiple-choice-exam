package com.fpt.onlineTest.service;

import com.fpt.onlineTest.dto.LessonDto;
import com.fpt.onlineTest.model.Lesson;

import java.util.List;

public interface LessonService {
//    create
    List<Lesson> createLesson(List<Lesson> lesson);
//    get by course id
List<LessonDto> getLessonByChapterId(Integer courseId);
//    update
    Lesson updateLesson(Integer lessonId, Lesson lesson);

//    delete by id
    void deleteLessonById(Integer lessonId);
}

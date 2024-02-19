package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.dto.LessonDto;
import com.fpt.onlineTest.model.Lesson;
import com.fpt.onlineTest.reponsitory.LessonRepository;
import com.fpt.onlineTest.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Override
    public List<Lesson> createLesson(List<Lesson> lesson) {
        return lessonRepository.saveAll(lesson);
    }

    @Override
    public List<LessonDto> getLessonByChapterId(Integer chapterId) {
        List<Lesson> lesson =lessonRepository.findLessonsByChapterId(chapterId);
        return lesson.stream()
                .map(this::convertLessonToDto)
                .collect(Collectors.toList());
    }

    public Lesson updateLesson(Integer lessonId, Lesson lesson) {
        Lesson existingLesson = lessonRepository.findById(lessonId).orElseThrow(()->new RuntimeException("Lesson not found!. id: "+lessonId));
        if(lesson.getLessonTitle()!=null){
            existingLesson.setLessonTitle(lesson.getLessonTitle());
        }
        return lessonRepository.save(existingLesson);
    }

    @Override
    public void deleteLessonById(Integer lessonId) {
        lessonRepository.deleteById(lessonId);
    }

    LessonDto convertLessonToDto(Lesson lesson){
        LessonDto lessonDto = new LessonDto();
        lessonDto.setLessonId(lesson.getLessonId());
        lessonDto.setLessonTitle(lesson.getLessonTitle());
        return lessonDto;
    }
}

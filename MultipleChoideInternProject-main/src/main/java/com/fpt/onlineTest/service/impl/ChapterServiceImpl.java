package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Chapter;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.Lesson;
import com.fpt.onlineTest.reponsitory.ChapterRepository;
import com.fpt.onlineTest.reponsitory.CourseRepository;
import com.fpt.onlineTest.reponsitory.LessonRepository;
import com.fpt.onlineTest.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private LessonRepository lessonRepository;

    //    @Override
//    public List<Chapter> createChapter( List<Chapter> newChapter) {
//        return chapterRepository.saveAll(newChapter);
//    }
    @Override
    public List<Chapter> createChapter(List<Chapter> newChapters) {
        List<Chapter> savedChapters = chapterRepository.saveAll(newChapters);
        // Cập nhật quan hệ giữa Lesson và Chapter
        for (Chapter chapter : savedChapters) {
            for (Lesson lesson : chapter.getLessons()) {
                lesson.setChapter(chapter);
            }
        }
        // Lưu lại dữ liệu Lesson đã cập nhật
        lessonRepository.saveAll(getAllLessonsFromChapters(savedChapters));

        return savedChapters;
    }

    private List<Lesson> getAllLessonsFromChapters(List<Chapter> chapters) {
        List<Lesson> lessons = new ArrayList<>();
        for (Chapter chapter : chapters) {
            lessons.addAll(chapter.getLessons());
        }
        return lessons;
    }

    public boolean isCourseExist(Integer courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        return course.isPresent();
    }

    @Override
    public List<Chapter> getCourseChapters(Integer courseId) {
        return chapterRepository.findCourseSChapter(courseId);
    }

    @Override
    public Chapter updateChapter(Integer chapterId, Chapter chapter) {
        Chapter existingChapter = chapterRepository.findById(chapterId).orElseThrow(() -> new RuntimeException("Not found chapter with id: " + chapterId));
        if (chapter.getDescription() != null) {
            existingChapter.setDescription(chapter.getDescription());
        }
        return chapterRepository.save(existingChapter);
    }

    @Override
    public void deleteChapterById(Integer chapterId) {
        chapterRepository.deleteById(chapterId);
    }

    @Override
    public void deleteCourseSChapter(Integer courseId) {
        chapterRepository.deleteCourseSChapter(courseId);
    }

}

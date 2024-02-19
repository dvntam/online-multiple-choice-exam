package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Chapter;

import java.util.List;
import java.util.Optional;

public interface ChapterService {
    //  -------  create  -------
//   Chapter createChapter( String title,Integer courseId);
    List<Chapter> createChapter(List<Chapter> newChapter);

    //  -------  read  -------

    //    get course's chapters
    List<Chapter> getCourseChapters( Integer courseId);

    //  -------  update  -------
    Chapter updateChapter(Integer chapterId, Chapter chapter);

    //  -------  delete  -------
    void deleteChapterById(Integer chapterId);

    void deleteCourseSChapter(Integer courseId);
}

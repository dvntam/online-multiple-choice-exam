package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
//get course chapter
    @Query("select ch from Chapter ch where ch.courses.courseId=:courseId")
    List<Chapter> findCourseSChapter(@Param("courseId") Integer courseId);
    //    delete course's chapter
    @Transactional
    @Modifying
    @Query("delete from Chapter ch where ch.courses.courseId=:courseId")
    void deleteCourseSChapter(Integer courseId);
}

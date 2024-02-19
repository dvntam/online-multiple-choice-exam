package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Query("select l from Lesson l where l.chapter.chapterId=:chapterId")
    List<Lesson> findLessonsByChapterId(@Param("chapterId") Integer chapterId);
}

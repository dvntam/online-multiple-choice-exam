package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.dto.CourseDto;
import com.fpt.onlineTest.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    //    get courses by teacher id
    @Query("select c from Course c where c.teacher.id = :teacherId")
    Page<Course> findCoursesByTeacherId(@Param("teacherId") Integer teacherId, Pageable pageable);

    //get courses by user id
    @Query("select c from Course c left join CoursesRegistration cr " +
            "on c.courseId = cr.course.courseId " +
            "where cr.user.userId =:userId")
    Page<Course> findCoursesByUserId(@Param("userId") Integer userId, Pageable pageable);

    //    get popular courses
    @Query("select c from Course c order by c.numberStudent desc ")
    List<Course> findTopPopularCourse();
//    get all courses
    @Query("select new com.fpt.onlineTest.dto.CourseDto(c.courseId,c.courseName,c.numberStudent,c.imageCourse,c.status,c.subject,t.id,t.fullName,t.imageTeacher) " +
            "from Course c " +
            "left join c.teacher t")
    Page<CourseDto> findAllCourses(Pageable pageable);
    // get course subject
    @Query("select c.subject from Course c where c.courseId=:courseId")
    String findCourseSubject(@Param("courseId") Integer courseId);
}

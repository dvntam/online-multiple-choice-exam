package com.fpt.onlineTest.dto;

import com.fpt.onlineTest.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private Integer numberStudent;
    private String imageCourse;
    private Boolean status;
    private String subject;
    private Integer teacherId;
    private String fullName;
    private String imageTeacher;
    private List<ChapterDto> chapters;

    public CourseDto(Integer courseId, String courseName, Integer numberStudent, String imageCourse, Boolean status, String subject, Integer teacherId, String fullName, String imageTeacher) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.numberStudent = numberStudent;
        this.imageCourse = imageCourse;
        this.status = status;
        this.subject = subject;
        this.teacherId = teacherId;
        this.fullName = fullName;
        this.imageTeacher = imageTeacher;
    }
}

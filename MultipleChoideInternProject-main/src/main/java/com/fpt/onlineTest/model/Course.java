package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Courses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(columnDefinition = "varchar(MAX)")
    @NotNull
    private String courseName;

    @Column
    @NotNull
    private Integer numberStudent;

    @Column(columnDefinition = "varchar(MAX)")
    @NotNull
    private String imageCourse;

    @Column
    @NotNull
    private Boolean status;

    @Column(columnDefinition = "varchar(40)")
    @NonNull
    private String subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @JsonIgnore
    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Chapter> chapters;

    @JsonIgnore
    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exam> exams;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoursesRegistration> courseRegistrations;

    public Course(Integer courseId, String courseName, String imageCourse, Integer numberStudent, Boolean status, String subject) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.imageCourse = imageCourse;
        this.numberStudent = numberStudent;
        this.status = status;
        this.subject = subject;
    }
    public Course(  String courseName, Integer numberStudent, String imageCourse, Boolean status, @NonNull String subject ) {

        this.courseName = courseName;
        this.numberStudent = numberStudent;
        this.imageCourse = imageCourse;
        this.status = status;
        this.subject = subject;

    }
}

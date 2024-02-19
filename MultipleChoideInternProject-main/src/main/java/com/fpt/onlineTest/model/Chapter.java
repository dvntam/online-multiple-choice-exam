package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Chapters")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"course"})
public class Chapter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chapterId;

    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    private String description;

    @JsonIgnoreProperties({"courseName","numberStudent","imageCourse","status","subject","teacher"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private Course courses;

//    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Chapter(String description) {
        this.description = description;
    }
}

package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Lesson")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Lesson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lessonId;

    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    private String lessonTitle;

    @JsonIgnore
    @JsonIgnoreProperties({"description","courses","lessons","chapterId"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapterId")
    private Chapter chapter;

    public Lesson(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }
}

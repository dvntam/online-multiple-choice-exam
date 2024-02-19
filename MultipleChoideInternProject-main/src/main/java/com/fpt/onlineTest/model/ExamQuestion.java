package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ExamQuestion")
public class ExamQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnoreProperties({"subject","level"})
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions question;

    @JsonIgnoreProperties({"numQuestion","duration","courses"})
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public ExamQuestion(Questions question, Exam exam) {
        this.question = question;
        this.exam = exam;
    }

}

package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Answers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    @Column(columnDefinition = "varchar(MAX)")
    @NotNull
    private String contentAnswer;

    @ManyToOne
    @JoinColumn(name = "questionId")
    @JsonBackReference
    Questions question;

    @Column(name = "status", columnDefinition = "varchar(100)")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "answer")
    List<ResultQuestion> resultQuestions;

    @JsonIgnore
    @OneToMany(mappedBy = "answer")
    List<UserAnswers> userAnswers;
}

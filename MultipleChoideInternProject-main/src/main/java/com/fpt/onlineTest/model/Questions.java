package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Questions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
//@ToString
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @Column(columnDefinition = "nvarchar(MAX)")
    @NotNull
    private String contentQuestion;

    @Column(columnDefinition = "nvarchar(100)")
    @NotNull
    private String subject;

    @Column(columnDefinition = "varchar(10)")
    @NotNull
    private String level;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JsonIgnoreProperties({"question", "resultQuestions", "status"})
    @JsonManagedReference
    List<Answer> answer;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private List<ExamQuestion> examQuestions;
}

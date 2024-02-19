package com.fpt.onlineTest.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ResultQuestions")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class ResultQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;

    @ManyToOne
    @JoinColumn(name = "answerId")
    Answer answer;

}

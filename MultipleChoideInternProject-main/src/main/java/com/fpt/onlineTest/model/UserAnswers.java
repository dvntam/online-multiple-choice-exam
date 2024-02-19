package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserAnswers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class UserAnswers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "answerId")
    Answer answer;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"userPass", "fullName", "email", "phone", "address", "imageUser", "role"})
    User user;


    @Column(name = "status", columnDefinition = "varchar(100)")
    private String status;
}

package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BanUser")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class BanUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer banUserId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"role", "userPass"})
    User user;

    @ManyToOne
    @JoinColumn(name = "examId")
    @JsonIgnoreProperties({"numQuestion", "timeStart", "timeEnd"})
    Exam exam;
}

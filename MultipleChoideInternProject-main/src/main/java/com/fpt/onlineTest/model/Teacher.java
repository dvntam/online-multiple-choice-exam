package com.fpt.onlineTest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Teachers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) unique")
    @NotNull
    private String username;

    @Column(columnDefinition = "varchar(MAX)")
    @NotNull
    private String userPass;

    @Column(columnDefinition = "nvarchar(50)")
    @NotNull
    private String fullName;
//
    @NotNull
    private String email;

    @Column(columnDefinition = "varchar(10) unique")
    @NotNull
    private String phone;

    @Column(columnDefinition = "nvarchar(100)")
    @NotNull
    private String address;

    @Column(columnDefinition = "varchar(255)")
    @NotNull
    private String imageTeacher;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courses;
}

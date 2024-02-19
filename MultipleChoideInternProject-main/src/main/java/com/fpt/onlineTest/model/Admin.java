package com.fpt.onlineTest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Admin")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    @Column(columnDefinition = "varchar(20) unique")
    @NotNull
    private String username;

    @Column(columnDefinition = "varchar(MAX)")
    @NotNull
    private String userPass;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

}

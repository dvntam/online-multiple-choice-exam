package com.fpt.onlineTest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Blogs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId;

    @Column(columnDefinition = "nvarchar(MAX)")
    private String titleBlog;

    @Column(columnDefinition = "nvarchar(MAX)")
    private String contentBlog;

    private String numberVisitors;


    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    public Blog(String titleBlog, String contentBlog, String numberVisitors) {
        this.titleBlog = titleBlog;
        this.contentBlog = contentBlog;
        this.numberVisitors = numberVisitors;
    }
}

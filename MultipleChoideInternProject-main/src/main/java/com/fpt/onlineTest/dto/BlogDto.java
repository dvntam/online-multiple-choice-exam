package com.fpt.onlineTest.dto;

import com.fpt.onlineTest.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    private Integer blogId;
    private String titleBlog;
    private String contentBlog;
    private String numberVisitors;
    private Integer userId;
    private String fullName;
    private String imageUser;
}

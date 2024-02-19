package com.fpt.onlineTest.service;

import com.fpt.onlineTest.dto.BlogDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.Blog;
import com.fpt.onlineTest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    // create blog
    Blog newBLog(Blog blog);

    //    get all blogs
    Page<BlogDto> getAll(Pageable pageable);

    // get blog by id
    BlogDto getBlogById(Integer blogId);

    //    get all blog by userId
    UserDto getBlogDtoByUserId(Integer userId, Pageable pageable, Optional<User> user);

    //    update blog
    Blog updateBlog(Integer id, Blog blog);

    //  delete blog by id
    void deleteBlogById(Integer blogId);

    //    delete all user blogs by userId
    void deleteAllBlogsByUserId(Integer userId);

    //    get top 5 blog have most visitors
    List<BlogDto> getTopBlogs();
}

package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.dto.BlogDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.Blog;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class BlogController {
    @Autowired
    private BlogService blogService;

    //    create blog
    @PostMapping("/blogs/add")
    public ResponseEntity<Object> createBlog(@RequestBody Blog newBlog) {
        try {
            blogService.newBLog(newBlog);
            return new ResponseEntity<>("Create success!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    get all blogs
    @GetMapping("/blogs")
    public ResponseEntity<Object> getAllBlogs(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        try {
            return new ResponseEntity<>(blogService.getAll(pageable), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    get blog by id
    @GetMapping("/blogs/{id}")
    public ResponseEntity<Object> getBlogById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(blogService.getBlogById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //    get all blog by userId
    @GetMapping("/blogs/user-blogs/{userId}")
    public ResponseEntity<Object> getAllBlogsByUserId(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            Optional<User> user) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            UserDto blogDtoPage = blogService.getBlogDtoByUserId(userId, pageable, user);

            return new ResponseEntity<>(blogDtoPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    update blog
    @PutMapping("/blogs/user-blog/update/blogId={blogId}")
    public ResponseEntity<Object> updateBlog(@PathVariable Integer blogId, @RequestBody Blog newBlog) {
        try {
            blogService.updateBlog(blogId, newBlog);
            return new ResponseEntity<>("Success!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    delete blog by blog id
    @DeleteMapping("/blogs/user-blogs/delete/{blogId}")
    public ResponseEntity<Object> deleteBlogById(@PathVariable Integer blogId) {
        try {
            blogService.deleteBlogById(blogId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    delete all blogs by userId
    @DeleteMapping("/blogs/user-blogs/delete-by/userId:{userId}")
    public ResponseEntity<Object> deleteBlogsByUserId(@PathVariable Integer userId) {
        try{
            blogService.deleteAllBlogsByUserId(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    get top blogs have most visitors
    @GetMapping("/blogs/popular-blogs")
    public ResponseEntity<Object> getPopularBlogs() {
        try {
            return new ResponseEntity<>(blogService.getTopBlogs(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

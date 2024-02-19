package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.dto.BlogDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.Blog;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.reponsitory.BlogRepository;
import com.fpt.onlineTest.reponsitory.UserRepository;
import com.fpt.onlineTest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    //    create new blog
    @Override
    public Blog newBLog(Blog blog) {
        User user = userRepository.findById(blog.getUser().getUserId()).orElse(null);
        if (user == null)
            return null;
        else
            blog.setUser(user);
        blog.setNumberVisitors("0");
        return blogRepository.save(blog);
    }

    //    get all blogs
    @Override
    public Page<BlogDto> getAll(Pageable pageable) {
        return blogRepository.findAllBlogs(pageable);
    }

    //    get blog by id
    @Override
    public BlogDto getBlogById(Integer blogId) {
        Optional<Blog> existingBlogOptional = blogRepository.findById(blogId);
        if (existingBlogOptional.isPresent()) {
            Blog existingBlog = existingBlogOptional.get();
            Integer curNumVisitors = Integer.valueOf(existingBlog.getNumberVisitors());
            curNumVisitors++;
            existingBlog.setNumberVisitors(String.valueOf(curNumVisitors));
            blogRepository.save(existingBlog);
        }
        return blogRepository.findBlogById(blogId);
    }

    //    get all blog by userId
    @Override
    public UserDto getBlogDtoByUserId(Integer userId, Pageable pageable, Optional<User> user) {
        Page<Blog> blogPage = blogRepository.findAllBlogsByUserId(userId, pageable);

//        Optional<User> user = userReponsitory.findById(userId);
        user = userRepository.findById(userId);

        UserDto userDto = new UserDto();
        if (user.isPresent()) {
            userDto.setUserId(user.get().getUserId());
            userDto.setFullName(user.get().getFullName());
            userDto.setEmail(user.get().getEmail());
            userDto.setPhone(user.get().getPhone());
            userDto.setAddress(user.get().getAddress());
            userDto.setImageUser(user.get().getImageUser());
        }
        userDto.setBlogs(blogPage.map(this::mapToDTO));
        return userDto;
    }

    //    update blog
    @Override
    public Blog updateBlog(Integer blogId, Blog blog) {
        Blog existingBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Not found blog with id: " + blogId));
        // Cập nhật các thuộc tính của đối tượng Blog từ đối tượng blog được truyền vào
        if (blog.getTitleBlog() != null) {
            existingBlog.setTitleBlog(blog.getTitleBlog());
        }

        if (blog.getContentBlog() != null) {
            existingBlog.setContentBlog(blog.getContentBlog());
        }

        if (blog.getNumberVisitors() != null) {
            existingBlog.setNumberVisitors(blog.getNumberVisitors());
        }
        return blogRepository.save(existingBlog);
    }

    //    delete blog by blog id
    @Override
    public void deleteBlogById(Integer blogId) {
        blogRepository.deleteById(blogId);
    }

    //    delete all blogs by userId
    @Override
    public void deleteAllBlogsByUserId(Integer userId) {
        blogRepository.deleteAllByUserId(userId);
    }

    //    get top 2 blogs have most visitors
    public List<BlogDto> getTopBlogs() {
        List<BlogDto> topBlogs = blogRepository.findTopBlogs();
        return topBlogs.subList(0, Math.min(5, topBlogs.size()));
    }

    private BlogDto mapToDTO(Blog blog) {
        BlogDto blogDto = new BlogDto();
        blogDto.setBlogId(blog.getBlogId());
        blogDto.setContentBlog(blog.getContentBlog());
        blogDto.setTitleBlog(blog.getTitleBlog());
        blogDto.setNumberVisitors(blog.getNumberVisitors());
        return blogDto;
    }

    public Long getUserId(Blog blog) {
        return Long.valueOf(blog.getUser().getUserId());
    }
}

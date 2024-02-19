package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.dto.BlogDto;
import com.fpt.onlineTest.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    @Query("select new com.fpt.onlineTest.dto.BlogDto(b.blogId, b.titleBlog, b.contentBlog, b.numberVisitors, u.userId, u.fullName, u.imageUser) " +
            "from Blog b " +
            "left join b.user u " +
            "order by b.numberVisitors desc ")
    List<BlogDto> findTopBlogs();

        @Query(value = "select new com.fpt.onlineTest.dto.BlogDto(b.blogId, b.titleBlog, b.contentBlog, b.numberVisitors, u.userId, u.fullName, u.imageUser) " +
            "from Blog b " +
            "left join b.user u " +
            "ORDER BY b.blogId asc ")
    Page<BlogDto> findAllBlogs(Pageable pageable);

    @Query("select new com.fpt.onlineTest.dto.BlogDto(b.blogId, b.titleBlog, b.contentBlog, b.numberVisitors, u.userId, u.fullName, u.imageUser) " +
            "from Blog b " +
            "left join b.user u " +
            "where b.blogId=:blogId")
    BlogDto findBlogById(@Param("blogId") Integer blogId);

    @Query("SELECT b FROM Blog b WHERE b.user.userId = :userId")
    Page<Blog> findAllBlogsByUserId(@Param("userId") Integer userId, Pageable pageable);

    @Transactional
    @Modifying
    @Query("DELETE FROM Blog b WHERE b.user.userId= :userId")
    void deleteAllByUserId(Integer userId);
}

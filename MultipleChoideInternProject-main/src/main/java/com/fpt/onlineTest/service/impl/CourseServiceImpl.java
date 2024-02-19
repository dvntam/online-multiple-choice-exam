package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.dto.*;
import com.fpt.onlineTest.model.*;
import com.fpt.onlineTest.reponsitory.*;
import com.fpt.onlineTest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private UserReponsitory userReponsitory;

    @Override
    public Course newCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourseById(Integer courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course updateCourse(Integer courseId, Course course) {
        Optional<Course> isExistingCourse = courseRepository.findById(courseId);
        if (isExistingCourse.isPresent()) {
            Course existingCourse = isExistingCourse.get();
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setNumberStudent(course.getNumberStudent());
            existingCourse.setImageCourse(course.getImageCourse());
            existingCourse.setStatus(course.getStatus());
            existingCourse.setSubject(course.getSubject());
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    @Override
    public Page<CourseDto> getAll(Pageable pageable) {
        System.out.println(courseRepository.findAllCourses(pageable));
        return courseRepository.findAllCourses(pageable);
    }

    @Override
    public TeacherDto getCoursesByTeacherId(Integer teacherId, Pageable pageable, Optional<Teacher> teacher) {
        Page<Course> coursePage = courseRepository.findCoursesByTeacherId(teacherId, pageable);
        teacher = teacherRepository.findById(teacherId);
        TeacherDto teacherDto = new TeacherDto();
        if (teacher.isPresent()) {
            teacherDto.setId(teacher.get().getId());
        }
        teacherDto.setCourses(coursePage.map(this::mapCourseToDto));
        return teacherDto;
    }

    @Override
    public UserDto getCoursesByUserIdtId(Integer userId, Pageable pageable, Optional<User> user) {
        Page<Course> coursePage = courseRepository.findCoursesByUserId(userId, pageable);

        user = userReponsitory.findById(userId);
        UserDto userDto = new UserDto();
        if (user.isPresent()) {
            userDto.setUserId(user.get().getUserId());
            userDto.setFullName(user.get().getFullName());
            userDto.setEmail(user.get().getEmail());
            userDto.setPhone(user.get().getPhone());
            userDto.setAddress(user.get().getAddress());
            userDto.setImageUser(user.get().getImageUser());
        }
        userDto.setCourses(coursePage.map(this::mapCourseToDto));

        return userDto;
    }


    @Override
    public List<Course> getPopuLarCourses() {
        List<Course> topPopularCourses = courseRepository.findTopPopularCourse();
        return topPopularCourses.subList(0, Math.min(5, topPopularCourses.size()));
    }

    @Override
    public Optional<CourseDto> getCourseDtoById(Integer courseId) {
        return courseRepository.findById(courseId).map(this::mapToDTO);
    }

    @Override
    public Optional<Course> getCourseById(Integer courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public String getSubject(Integer courseId) {
        return courseRepository.findCourseSubject(courseId);
    }

    public CourseDto mapCourseToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setNumberStudent(course.getNumberStudent());
        courseDto.setImageCourse(course.getImageCourse());
        courseDto.setStatus(course.getStatus());
        courseDto.setSubject(course.getSubject());
        return courseDto;
    }

    private CourseDto mapToDTO(Course course) {
        CourseDto courseDTO = new CourseDto();
        // Map fields from Course entity to CourseDTO
        courseDTO.setCourseId(course.getCourseId());
        courseDTO.setCourseName(course.getCourseName());
        courseDTO.setNumberStudent(course.getNumberStudent());
        courseDTO.setImageCourse(course.getImageCourse());
        courseDTO.setStatus(course.getStatus());
        courseDTO.setSubject(course.getSubject());
        courseDTO.setTeacherId(course.getTeacher().getId());

        List<ChapterDto> chapterDtoList = new ArrayList<>();
        List<Chapter> chapterList = chapterRepository.findCourseSChapter(courseDTO.getCourseId());
        for (Chapter chapter : chapterList) {
            ChapterDto chapterDto = new ChapterDto();
            chapterDto.setChapterId(chapter.getChapterId());
            chapterDto.setDescription(chapter.getDescription());

            List<LessonDto> lessonDtoList = new ArrayList<>();
            List<Lesson> lessonList = lessonRepository.findLessonsByChapterId(chapterDto.getChapterId());
            for (Lesson lesson : lessonList) {
                LessonDto lessonDto = new LessonDto();
                lessonDto.setLessonId(lesson.getLessonId());
                lessonDto.setLessonTitle(lesson.getLessonTitle());
                lessonDtoList.add(lessonDto);
            }
            chapterDto.setLessons(lessonDtoList);
            chapterDtoList.add(chapterDto);
            courseDTO.setChapters(chapterDtoList);
        }
        courseDTO.setChapters(chapterDtoList);
        return courseDTO;
    }

    //  get teacher id
    Integer getTeacherId() {
        return null;
    }
}

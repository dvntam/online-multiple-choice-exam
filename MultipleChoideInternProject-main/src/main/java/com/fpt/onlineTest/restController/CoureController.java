package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.dto.CourseDto;
import com.fpt.onlineTest.dto.TeacherDto;
import com.fpt.onlineTest.dto.UserDto;
import com.fpt.onlineTest.model.Course;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.service.CourseService;
import com.fpt.onlineTest.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class CoureController {
    @Autowired
    CourseService courseService;

    @Autowired
    ImageUploadService imageUploadService;

    //    create course
    @PostMapping(value = "/courses/create-course", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Course> createNewCourse(@RequestParam("imageCourse") MultipartFile file,
                                                  @RequestParam("courseName") String courseName,
                                                  @RequestParam("numberStudent") Integer numberStudent,
                                                  @RequestParam("status") Boolean status,
                                                  @RequestParam("subject") String subject,
                                                  @RequestParam("teacherId") Teacher teacherId) {
        try {
            Course course = new Course();
            String generatedFileName = "http://127.0.0.1:8080/api/v1/file/upload/" + imageUploadService.storeFile(file);
            course.setCourseName(courseName);
            course.setNumberStudent(numberStudent);
            course.setImageCourse(generatedFileName);
            course.setStatus(status);
            course.setSubject(subject);
            course.setTeacher(teacherId);
            return new ResponseEntity<>(courseService.newCourse(course), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete course by id
    @DeleteMapping("/courses/delete-course/{courseId}")
    public void deleteCourseById(@PathVariable Integer courseId) {
        try {
            courseService.deleteCourseById(courseId);
            new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update course
    @PutMapping("/courses/update-course/{courseId}")
    public ResponseEntity<Course> updateCourseByCourseId(@PathVariable Integer courseId, @RequestBody Course newCourse) {
        try {
            return new ResponseEntity<>(courseService.updateCourse(courseId, newCourse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    get all course
    @GetMapping("/courses")
    public ResponseEntity<Page<CourseDto>> getAllCourses(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return new ResponseEntity<>(courseService.getAll(pageable), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //    get course by teacher id
    @GetMapping("/courses/teacher-courses/{teacherId}")
    public ResponseEntity<TeacherDto> getCourseByTeacherId(
            @PathVariable Integer teacherId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            Optional<Teacher> teacher
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            TeacherDto teacherCourseDto = courseService.getCoursesByTeacherId(teacherId, pageable, teacher);

            return new ResponseEntity<>(teacherCourseDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    get course by id
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Integer courseId) {
        try {
            Optional<CourseDto> courseDto = courseService.getCourseDtoById(courseId);
            return courseDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get course by student id
    @GetMapping("/courses/student-courses/{userId}")
    public ResponseEntity<UserDto> getCoursesByStudentId(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            Optional<User> user) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            System.out.println("controller user id: " + userId);
            UserDto userCourseDto = courseService.getCoursesByUserIdtId(userId, pageable, user);

            return new ResponseEntity<>(userCourseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    get top popular course
    @GetMapping("/courses/popular-courses")
    public ResponseEntity<List<Course>> getTopPopularCourses() {
        try {
            return new ResponseEntity<>(courseService.getPopuLarCourses(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // get course image
    @GetMapping("/course/{courseId}/image")
    public ResponseEntity<Resource> getCourseImage(@PathVariable Integer courseId) {
        Optional<Course> courseOptional = courseService.getCourseById(courseId);
        if (courseOptional.isPresent()) {
            Course course = new Course();
            if (course.getImageCourse() != null) {
                Path imagePath = Paths.get(course.getImageCourse());
                Resource resource = new FileSystemResource(imagePath);

                if (resource.exists()) {
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG) // Hoặc MediaType.IMAGE_PNG tùy loại ảnh
                            .body(resource);
                }
            }

        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/course/{id}/subject")
    public ResponseEntity<String> getCourseSubject(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(courseService.getSubject(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

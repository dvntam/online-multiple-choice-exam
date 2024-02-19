//package com.fpt.onlineTest.restController;
//
//import com.fpt.onlineTest.model.Teacher;
//import com.fpt.onlineTest.model.User;
////import com.fpt.onlineTest.service.TeacherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@CrossOrigin("*")
//@RequestMapping("/api/v1")
//public class TeacherController {
////
////    @Autowired
////    TeacherService teacherService;
//
//
//
//    //--------------Get All-------------------
//    @GetMapping("teacher/getAll")
//    public List<Teacher> getAll(){
//        return teacherService.findAll();
//    }
//    //--------------Create User-------------------
//    @PostMapping("teacher/addTeacher")
//    public ResponseEntity<Teacher> createUser(@RequestBody Teacher teacher){
//        Teacher addTeacher = teacherService.save(teacher);
//        return ResponseEntity.ok(addTeacher);
//    }
//    //--------------Update User-------------------
//
//    @PutMapping("teacher/updateTeacher/{teacherId}")
//    public Teacher update(@PathVariable("teacherId") Integer teacherId, @RequestBody Teacher teacher){
//        return teacherService.saveAndFlush(teacher);
//    }
//
//    //--------------Delete User-------------------
//    @DeleteMapping("teacher/deleteTeacher/{teacherId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable("teacherId")Integer teacherId ){
//        teacherService.deleteById(teacherId);
//        return  ResponseEntity.notFound().build();
//    }
//}

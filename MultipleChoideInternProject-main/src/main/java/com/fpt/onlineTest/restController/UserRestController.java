package com.fpt.onlineTest.restController;


import com.fpt.onlineTest.model.ResultExam;
import com.fpt.onlineTest.model.Role;
import com.fpt.onlineTest.model.Teacher;
import com.fpt.onlineTest.model.User;
//import com.fpt.onlineTest.service.TeacherService;
import com.fpt.onlineTest.reponsitory.UserReponsitory;
import com.fpt.onlineTest.service.ImageUploadService;
import com.fpt.onlineTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserRestController {
    @Autowired
    UserService userService;
    //
//    @Autowired
//    TeacherService teacherService;
    @Autowired
    ImageUploadService imageUploadService;
    @Autowired
    private UserReponsitory userReponsitory;

    //--------------Get All-------------------
    @GetMapping("users/getAll")
    public List<User> getAll() {
        return userService.findAll();
    }

    //--------------Create User-------------------
    @PostMapping("users/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User addUser = userService.save(user);
        return ResponseEntity.ok(addUser);
    }

    //--------------Update User-------------------
    @PutMapping("users/updateUser/{userId}")
    public ResponseEntity<Object> update(@PathVariable("userId") Integer userId,
                                         @RequestBody User user) {
        try {
            // Trả về danh sách kết quả
            return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    update user image
    @PutMapping(value = "users/update/image/{userId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> update(@PathVariable("userId") Integer userId,
                                         @RequestParam("imageUser") MultipartFile file
    ) {
        try {
            Optional<User> existingUser = userReponsitory.findById(userId);

            String generatedFileName = imageUploadService.storeFile(file);
            User user = new User();
            user.setImageUser(generatedFileName);
            // Trả về danh sách kết quả
            return new ResponseEntity<>(userService.updateUserImg(userId, user), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý ngoại lệ và trả về thông báo lỗi
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //--------------Delete User-------------------
    @DeleteMapping("users/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Integer userId) {
        boolean delete = userService.deleteById(userId);
        if (delete) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //    ------------ hoviet -------------------//

//
}


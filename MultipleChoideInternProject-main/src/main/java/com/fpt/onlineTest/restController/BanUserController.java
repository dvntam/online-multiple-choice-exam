package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.BanUser;
import com.fpt.onlineTest.model.ResponseObject;
import com.fpt.onlineTest.model.ResultExamStats;
import com.fpt.onlineTest.service.BanUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/ban-user")
public class BanUserController {
    @Autowired
    BanUserService banUserService;

    @GetMapping("")
    public ResponseEntity<List<BanUser>> getAllBanUser() {
        try{
            return new ResponseEntity<>(banUserService.getAllBanUser(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/examId/{id}")
    public ResponseEntity<List<BanUser>> getBanUserByExamId(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(banUserService.getBanUserByExamId(id), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<List<BanUser>> getBanUserByUserId(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(banUserService.getBanUserByUserId(id), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/userId-and-examId/{userId}/{examId}")
    public ResponseEntity<List<BanUser>> getBanUserByUserIdAndExamId(@PathVariable Integer userId, @PathVariable Integer examId) {
        try{
            return new ResponseEntity<>(banUserService.getBanUserByUserIdAndExamId(userId, examId), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<BanUser> createBanUser(@RequestBody BanUser banUser) {
        try {
            banUserService.createBanUser(banUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBanUser(@PathVariable Integer id) {
        try {
            banUserService.deleteBanUser(id);
            new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/exists-ban-user/{userId}/{examId}")
    public ResponseEntity<ResponseObject> existsBanUser(@PathVariable Integer userId, @PathVariable Integer examId) {
        try {
            List<BanUser> banUser = banUserService.getBanUserByUserIdAndExamId(userId, examId);
            if (banUser != null && !banUser.isEmpty()){
                return new ResponseEntity<>(new ResponseObject("failed", "This user cannot take this test!", ""), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new ResponseObject("success", "This user can take this test!", ""), HttpStatus.OK);
            }

        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

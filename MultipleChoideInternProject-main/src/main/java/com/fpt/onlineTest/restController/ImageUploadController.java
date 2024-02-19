package com.fpt.onlineTest.restController;

import com.fpt.onlineTest.model.ResponseObject;
import com.fpt.onlineTest.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/file/upload")
public class ImageUploadController {
    @Autowired
    ImageUploadService imageUploadService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String generatedFileName = "http://127.0.0.1:8080/api/v1/file/upload/" + imageUploadService.storeFile(file);
            return new ResponseEntity<>(new ResponseObject("success", "Upload file successfully!", generatedFileName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseObject("failed", e.getMessage(), ""), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = imageUploadService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadedFiles() {
        try {
            List<String> urls = imageUploadService.loadAll().map(path -> {
                return MvcUriComponentsBuilder.fromMethodName(ImageUploadController.class, "readDetailFile", path.getFileName().toString()).build().toUri().toString();
            }).collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("success", "List files successfully!", urls));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseObject("failed", "List files failed!", new String[] {}));
        }
    }
}

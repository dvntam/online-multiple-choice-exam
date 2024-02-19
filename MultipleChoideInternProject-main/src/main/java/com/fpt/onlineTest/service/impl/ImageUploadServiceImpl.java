package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.service.ImageUploadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {
    private final Path storageFolder = Paths.get("images");

    public ImageUploadServiceImpl() {
        try {
            Files.createDirectories(storageFolder);
        } catch (Exception e) {
            throw new RuntimeException("Cannot initialize storage", e);
        }
    }

    private boolean isImageFile(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png", "jpg", "jpeg", "bmp"}).contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            if(file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }
            //check file is image?
            if(!isImageFile(file)){
                throw new RuntimeException("You can only upload image file");
            }
            // File must be <= 5Mb
            float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
            if(fileSizeInMegabytes > 5.0f) {
                throw new RuntimeException("File must be <= 5Mb");
            }
            // File must be rename
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("", "");
            generatedFileName = generatedFileName + "." + fileExtension;
            Path destinationFilePath = this.storageFolder.resolve(Paths.get(generatedFileName)).normalize().toAbsolutePath();
            if(!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory.");
            }
            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to storage file", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.storageFolder, 1).filter(path -> {
                return !path.equals(this.storageFolder) && !path.toString().contains("._");
            }).map(this.storageFolder::relativize);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load store files", e);
        }
    }

    @Override
    public byte[] readFileContent(String fileName) {
        try {
            Path file = storageFolder.resolve(fileName);
            UrlResource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            }else{
                throw new RuntimeException("Could not read file: " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not read file: " + fileName, e);
        }
    }
}

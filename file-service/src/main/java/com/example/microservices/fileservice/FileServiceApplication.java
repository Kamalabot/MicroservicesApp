package com.example.microservices.fileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@RestController
@RequestMapping("/files")
public class FileServiceApplication {

    private static final String UPLOAD_DIR = "/uploads/";

    public static void main(String[] args) {
        SpringApplication.run(FileServiceApplication.class, args);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + file.getOriginalFilename()));
            return "File uploaded: " + file.getOriginalFilename();
        } catch (IOException e) {
            return "Failed to upload file";
        }
    }

    @GetMapping("/{filename}")
    public byte[] getFile(@PathVariable String filename) throws IOException {
        return Files.readAllBytes(Paths.get(UPLOAD_DIR + filename));
    }
}


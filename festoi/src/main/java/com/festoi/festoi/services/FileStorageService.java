package com.festoi.festoi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    @Value(("${upload.dir}"))
    private String uploadDir;

    public void storeFile(MultipartFile file,String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath + " I am the file path from storeFile function");
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public String generateFileUrl(String fileName) {
        return "/photos/" + fileName;
    }
}

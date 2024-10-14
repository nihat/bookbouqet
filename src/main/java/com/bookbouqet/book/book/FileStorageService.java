package com.bookbouqet.book.book;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    @Value("${application.file.upload.pictures-output-path}")
    private String uploadDir;

    public String saveFile(@NonNull MultipartFile sourceFile,
                           @NonNull Integer userId) {
        String fileUploadSubPath = "users" + File.separator + userId;

        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(@NonNull MultipartFile sourceFile,
                              @NonNull String fileUploadSubPath) {

        final String fileUploadFolder = uploadDir + File.separator + fileUploadSubPath;
        File targetFile = new File(fileUploadFolder);
        if (!targetFile.exists()) {
            boolean isFolderCreated = targetFile.mkdir();
            if (!isFolderCreated) {
                log.error("Could not create folder " + fileUploadFolder);
            }
            final String fileExtension = getFileExtension(sourceFile);
            final String targetFilePath = fileUploadFolder + File.separator + System.currentTimeMillis() + fileExtension;
            Path targetPath = Paths.get(targetFilePath);
            try {
                Files.write(targetPath, sourceFile.getBytes());
                log.info("Successfully uploaded " + sourceFile.getOriginalFilename() + " to " + targetFilePath);
            } catch (IOException e) {
               log.error("Could not write file " + targetFilePath);
            }
            return targetFilePath;
        }
    }

    private String getFileExtension(MultipartFile sourceFile) {
        String fileName = sourceFile.getOriginalFilename();
        if (StringUtils.isBlank(fileName)) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return fileName.substring(index);
    }
}

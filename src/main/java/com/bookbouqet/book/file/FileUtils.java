package com.bookbouqet.book.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileUtils {


    public static byte[] copyFileFromDir(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        try {
            return Files.readAllBytes(Path.of(filePath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}

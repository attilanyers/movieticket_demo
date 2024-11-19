package com.company.movieticket.file;

import com.company.movieticket.exception.BaseException;
import com.company.movieticket.exception.ErrorEnum;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService {
    public static final String STORE_FILES_IN_FOLDER = "files";

    public void deleteFile(String url) {
        if (url != null) {
            getFile(url).delete();
        }
    }

    public String saveFile(String newUrl) {
        return saveFile(newUrl, null);
    }

    public String saveFile(String url, String currentFileName) {
        if (url == null) {
            deleteFile(currentFileName);
            return null;
        }
        if (url.startsWith("/")) {
            String fileNamePart = url.substring(url.lastIndexOf("/") + 1);
            if (Objects.equals(fileNamePart, currentFileName)) {
                return currentFileName;
            }
        } else {
            throw new BaseException(ErrorEnum.BAD_REQUEST, "Invalid url");
        }

        if (!url.startsWith("data:")) {
            throw new IllegalStateException("Url must be a data url");
        }

        String base64 = url.substring(url.indexOf(",") + 1);
        byte[] bytes = Base64.getDecoder().decode(base64);
        String contentType = url.substring("data:".length(), url.indexOf(";"));
        String extension =
                switch (contentType) {
                    case "video/mp4" -> "mp4";
                    case "image/jpeg" -> "jpg";
                    case "image/png" -> "png";
                    default -> throw new IllegalStateException();
                };

        String fileName = UUID.randomUUID() + "." + extension;
        writeFile(fileName, bytes);
        return fileName;
    }

    protected void writeFile(String fileName, byte[] bytes) {
        try {
            Files.write(getFile(fileName).toPath(), bytes);
        } catch (IOException e) {
            throw new BaseException(ErrorEnum.GENERAL_ERROR, e);
        }
    }

    public ResponseEntity<InputStreamResource> getFileResponse(String fileName) {
        String extension = fileName.substring(fileName.indexOf("."));
        String contentType =
                switch (extension) {
                    case "mp4" -> "video/mp4";
                    case "jpg" -> "image/jpeg";
                    case "png" -> "image/png";
                    default -> "application/octet-stream";
                };
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(contentType))
                    .body(new InputStreamResource(new FileInputStream(getFile(fileName))));
        } catch (FileNotFoundException e) {
            throw new BaseException(ErrorEnum.GENERAL_ERROR, "File not found");
        }
    }

    private File getFile(String fileName) {
        return new File(STORE_FILES_IN_FOLDER, fileName);
    }
}

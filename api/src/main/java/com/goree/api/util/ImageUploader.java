package com.goree.api.util;

import com.goree.api.exception.ImageUploadException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Component
public class ImageUploader {
    @Value("${file.upload.path}")
    private String staticPath;

    public String upload(MultipartFile file) {
        String fileName = new Date().getTime() + file.getOriginalFilename();

        Objects.requireNonNull(staticPath);

        try {
            FileUtils.forceMkdir(new File(staticPath));
            File imagePath = new File(staticPath + fileName);
            file.transferTo(imagePath);
        } catch (IOException e) {
            throw new ImageUploadException("Error occured while copy image file. (fileName : "+fileName+")", e);
        }

        return fileName;
    }
}

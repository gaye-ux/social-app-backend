package com.socialapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UploadService {

    private static final Map<String, Object> EMPTY_UPLOAD_OPTIONS = Collections.emptyMap();

    private final Map<String, String> config = new ConcurrentHashMap<>();
    private Cloudinary cloudinary;

    @Value("${cloudinary.cloudName}")
    public void setCloudName(String cloudName) {
        config.put("cloud_name", cloudName);
    }

    @Value("${cloudinary.apiKey}")
    public void setApiKey(String apiKey) {
        config.put("api_key", apiKey);
    }

    @Value("${cloudinary.apiSecret}")
    public void setApiSecret(String apiSecret) {
        config.put("api_secret", apiSecret);
    }

    @PostConstruct
    public void init() {
        this.cloudinary = new Cloudinary(config);
    }

    public String uploadFile(File file) {
        try {
            Map<?, ?> uploadResult = cloudinary.uploader()
                    .upload(file, ObjectUtils.emptyMap()); // or your upload options
            return (String) uploadResult.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to Cloudinary", e);
        }
    }


    // Custom exception for better error handling
    public static class FileUploadException extends RuntimeException {
        public FileUploadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
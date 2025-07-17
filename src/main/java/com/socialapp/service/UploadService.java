package com.socialapp.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.net.URI;


@Service
public class UploadService {

    private Cloudinary cloudinary;
    private final Map<String, String> config = new ConcurrentHashMap<>();

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

    public String uploadFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new FileUploadException("File cannot be null or empty", null);
        }

        try {
            Map<?, ?> uploadResult = cloudinary.uploader()
                    //auto to allow file media types
                    .upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
            String url = (String) uploadResult.get("secure_url");

            // First remove all whitespace
            String cleanUrl = url.replaceAll("\\s+", "");

            // Fix common protocol formatting issue
            cleanUrl = cleanUrl.replace("https: //", "https://");

            try {
                URI uri = new URI(cleanUrl);
                System.out.println("The URL: "+ uri);
                return uri.toASCIIString();
            } catch (URISyntaxException e) {
                // Fallback to manual cleaning if URI parsing fails
                return cleanUrl.replaceAll("[^a-zA-Z0-9:/._-]", "");
            }
        } catch (IOException e) {
            throw new FileUploadException("Upload failed", e);
        }
    }

    public static class FileUploadException extends RuntimeException {
        public FileUploadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
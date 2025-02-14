package kz.edu.astanait.afisha.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

public interface EventImageUploader {

    void uploadImage(UUID eventId, MultipartFile file);
}

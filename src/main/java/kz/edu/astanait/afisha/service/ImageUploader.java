package kz.edu.astanait.afisha.service;

import kz.edu.astanait.afisha.domain.ImageMetadata;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploader {

    ImageMetadata uploadImage(MultipartFile file);
}

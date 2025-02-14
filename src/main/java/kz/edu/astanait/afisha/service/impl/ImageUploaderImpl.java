package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.ImageMetadata;
import kz.edu.astanait.afisha.entity.ImageEntity;
import kz.edu.astanait.afisha.exception.FailedImageUploadException;
import kz.edu.astanait.afisha.mapper.ImageMapper;
import kz.edu.astanait.afisha.repository.ImageRepository;
import kz.edu.astanait.afisha.service.ImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageUploaderImpl implements ImageUploader {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    @Transactional
    public ImageMetadata uploadImage(final MultipartFile file) {
        try {
            final ImageEntity image = imageMapper.mapToImageEntity(file);
            final ImageEntity savedImage = imageRepository.save(image);
            return imageMapper.mapToImageMetadata(savedImage);
        } catch (final IOException exception) {
            throw new FailedImageUploadException("Cannot upload image", exception);
        }
    }
}

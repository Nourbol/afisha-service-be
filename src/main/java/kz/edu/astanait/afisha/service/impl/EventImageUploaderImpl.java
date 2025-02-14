package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.ImageMetadata;
import kz.edu.astanait.afisha.entity.EventImageEntity;
import kz.edu.astanait.afisha.exception.RecordNotFoundException;
import kz.edu.astanait.afisha.repository.EventImageRepository;
import kz.edu.astanait.afisha.repository.EventRepository;
import kz.edu.astanait.afisha.repository.ImageRepository;
import kz.edu.astanait.afisha.service.EventImageUploader;
import kz.edu.astanait.afisha.service.ImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EventImageUploaderImpl implements EventImageUploader {

    private final ImageUploader imageUploader;
    private final ImageRepository imageRepository;
    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;

    @Override
    public void uploadImage(final UUID eventId, final MultipartFile file) {
        if (!eventRepository.existsById(eventId)) {
            throw RecordNotFoundException.eventNotFound(eventId);
        }
        final ImageMetadata imageMetadata = imageUploader.uploadImage(file);
        final EventImageEntity eventImage = new EventImageEntity(
                eventRepository.getReferenceById(eventId),
                imageRepository.getReferenceById(imageMetadata.id()),
                !eventImageRepository.existsByEventId(eventId)
        );
        eventImageRepository.save(eventImage);
    }
}

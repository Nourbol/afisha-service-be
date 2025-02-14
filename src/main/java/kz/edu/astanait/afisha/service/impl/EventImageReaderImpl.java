package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.Image;
import kz.edu.astanait.afisha.entity.EventImageEntity;
import kz.edu.astanait.afisha.exception.RecordNotFoundException;
import kz.edu.astanait.afisha.mapper.ImageMapper;
import kz.edu.astanait.afisha.repository.EventImageRepository;
import kz.edu.astanait.afisha.service.EventImageReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventImageReaderImpl implements EventImageReader {

    private final ImageMapper imageMapper;
    private final EventImageRepository eventImageRepository;

    @Override
    public Image getCover(final UUID eventId) {
        final EventImageEntity eventImage = eventImageRepository.findByEventIdAndIsCoverTrue(eventId)
                .orElseThrow(() -> new RecordNotFoundException("Event with id %s has no cover".formatted(eventId)));
        return imageMapper.mapToImage(eventImage.getImage());
    }
}

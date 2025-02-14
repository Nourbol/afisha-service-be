package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.repository.EventImageRepository;
import kz.edu.astanait.afisha.service.EventImageModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EventImageModifierImpl implements EventImageModifier {

    private final EventImageRepository eventImageRepository;

    @Override
    public void deleteImage(final UUID eventId, final UUID imageId) {
        eventImageRepository.deleteByEventIdAndImageId(eventId, imageId);
    }
}

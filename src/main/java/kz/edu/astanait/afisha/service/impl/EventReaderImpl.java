package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.Event;
import kz.edu.astanait.afisha.domain.EventPreview;
import kz.edu.astanait.afisha.entity.EventEntity;
import kz.edu.astanait.afisha.exception.RecordNotFoundException;
import kz.edu.astanait.afisha.mapper.EventMapper;
import kz.edu.astanait.afisha.repository.EventRepository;
import kz.edu.astanait.afisha.service.EventReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventReaderImpl implements EventReader {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public Page<EventPreview> getEventPreviews(final Pageable pageable, final String query) {
        final Page<EventEntity> events = query == null
                ? eventRepository.findAll(pageable)
                : eventRepository.findAllByTitleContaining(query, pageable);
        return events.map(eventMapper::mapToEventPreview);
    }

    @Override
    public Page<EventPreview> getCategoryEvents(final UUID categoryId, final Pageable pageable) {
        return new PageImpl<>(eventRepository.findAll(pageable)
                .stream()
                .filter(event -> event.getCategory().getId().equals(categoryId))
                .map(eventMapper::mapToEventPreview).toList());
    }

    @Override
    public Event getEvent(final UUID id) {
        return eventRepository.findById(id)
                .map(eventMapper::mapToEvent)
                .orElseThrow(() -> RecordNotFoundException.eventNotFound(id));
    }
}

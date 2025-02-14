package kz.edu.astanait.afisha.service.impl;

import kz.edu.astanait.afisha.domain.Event;
import kz.edu.astanait.afisha.domain.EventSaveRequest;
import kz.edu.astanait.afisha.entity.EventEntity;
import kz.edu.astanait.afisha.exception.RecordNotFoundException;
import kz.edu.astanait.afisha.mapper.EventMapper;
import kz.edu.astanait.afisha.repository.CategoryRepository;
import kz.edu.astanait.afisha.repository.EventRepository;
import kz.edu.astanait.afisha.service.EventModifier;
import kz.edu.astanait.afisha.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EventModifierImpl implements EventModifier {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryValidator categoryValidator;

    @Override
    public Event createEvent(final EventSaveRequest request) {
        final UUID categoryId = request.categoryId();
        categoryValidator.assertExistence(categoryId);
        final EventEntity event
                = eventMapper.mapToEventEntity(request, categoryRepository.getReferenceById(categoryId));
        final EventEntity savedEvent = eventRepository.save(event);
        return eventMapper.mapToEvent(savedEvent);
    }

    @Override
    public Event updateEvent(final UUID id, final EventSaveRequest request) {
        final EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> RecordNotFoundException.eventNotFound(id));
        final UUID newCategoryId = request.categoryId();
        if (!event.getCategory().getId().equals(newCategoryId)) {
            categoryValidator.assertExistence(newCategoryId);
            event.setCategory(categoryRepository.getReferenceById(newCategoryId));
        }
        eventMapper.mapToEventEntity(event, request);
        return eventMapper.mapToEvent(event);
    }

    @Override
    public void deleteEvent(final UUID id) {
        eventRepository.deleteById(id);
    }
}

package kz.edu.astanait.afisha.mapper;

import kz.edu.astanait.afisha.domain.Event;
import kz.edu.astanait.afisha.domain.EventPreview;
import kz.edu.astanait.afisha.domain.EventSaveRequest;
import kz.edu.astanait.afisha.entity.CategoryEntity;
import kz.edu.astanait.afisha.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final CategoryMapper categoryMapper;

    public EventPreview mapToEventPreview(final EventEntity event) {
        return new EventPreview(
                event.getId(),
                event.getTitle(),
                event.getStartAt(),
                event.getShortLocation(),
                event.getPrice()
        );
    }

    public Event mapToEvent(final EventEntity event) {
        return new Event(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartAt(),
                event.getLocation(),
                event.getPrice(),
                categoryMapper.mapToCategory(event.getCategory())
        );
    }

    public EventEntity mapToEventEntity(final EventSaveRequest request, final CategoryEntity category) {
        final EventEntity event = new EventEntity();
        event.setCategory(category);
        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setStartAt(request.startTime());
        event.setLocation(request.location());
        event.setShortLocation(request.shortLocation());
        event.setPrice(request.price());
        return event;
    }

    public void mapToEventEntity(final EventEntity event, final EventSaveRequest request) {
        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setStartAt(request.startTime());
        event.setLocation(request.location());
        event.setShortLocation(request.shortLocation());
        event.setPrice(request.price());
    }
}

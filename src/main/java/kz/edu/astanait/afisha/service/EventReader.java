package kz.edu.astanait.afisha.service;

import kz.edu.astanait.afisha.domain.Event;
import kz.edu.astanait.afisha.domain.EventPreview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface EventReader {

    Page<EventPreview> getEventPreviews(Pageable pageable, String query);

    Page<EventPreview> getCategoryEvents(UUID categoryId, Pageable pageable);

    Event getEvent(UUID id);
}

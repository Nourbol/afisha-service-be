package kz.edu.astanait.afisha.service;

import kz.edu.astanait.afisha.domain.Event;
import kz.edu.astanait.afisha.domain.EventSaveRequest;
import java.util.UUID;

public interface EventModifier {

    Event createEvent(EventSaveRequest request);

    Event updateEvent(UUID id, EventSaveRequest request);

    void deleteEvent(UUID id);
}

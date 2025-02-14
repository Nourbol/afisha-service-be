package kz.edu.astanait.afisha.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.afisha.domain.Event;
import kz.edu.astanait.afisha.domain.EventPreview;
import kz.edu.astanait.afisha.domain.EventSaveRequest;
import kz.edu.astanait.afisha.service.EventModifier;
import kz.edu.astanait.afisha.service.EventReader;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import jakarta.validation.Valid;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Events")
@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EventController {

    private final EventReader eventReader;
    private final EventModifier eventModifier;

    @PageableAsQueryParam
    @GetMapping(path = "/events", produces = APPLICATION_JSON_VALUE)
    public Page<EventPreview> getEvents(final @Parameter(hidden = true) Pageable pageable,
                                        final @RequestParam(required = false) String query) {
        return eventReader.getEventPreviews(pageable, query);
    }

    @GetMapping(path = "/events/{id}", produces = APPLICATION_JSON_VALUE)
    public Event getEvent(final @PathVariable("id") UUID id) {
        return eventReader.getEvent(id);
    }

    @PageableAsQueryParam
    @GetMapping(path = "/categories/{categoryId}/events", produces = APPLICATION_JSON_VALUE)
    public Page<EventPreview> getCategoryEvents(final @PathVariable("categoryId") UUID categoryId,
                                                final @Parameter(hidden = true) Pageable pageable) {
        return eventReader.getCategoryEvents(categoryId, pageable);
    }

    @PostMapping(path = "/events", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> createEvent(final @RequestBody @Valid EventSaveRequest request) {
        final Event event = eventModifier.createEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @PutMapping(path = "/events/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Event> updateEvent(final @PathVariable("id") UUID id,
                                             final @RequestBody @Valid EventSaveRequest request) {
        final Event event = eventModifier.updateEvent(id, request);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping(path = "/events/{id}")
    public ResponseEntity<Void> deleteEvent(final @PathVariable("id") UUID id) {
        eventModifier.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}

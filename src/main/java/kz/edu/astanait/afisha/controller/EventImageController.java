package kz.edu.astanait.afisha.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kz.edu.astanait.afisha.domain.Image;
import kz.edu.astanait.afisha.service.EventImageModifier;
import kz.edu.astanait.afisha.service.EventImageReader;
import kz.edu.astanait.afisha.service.EventImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.http.MediaType.valueOf;

@Tag(name = "Event images")
@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventImageController {

    private final EventImageUploader eventImageUploader;
    private final EventImageReader eventImageReader;
    private final EventImageModifier eventImageModifier;

    @PostMapping(path = "/{id}/images", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadImage(@PathVariable("id") UUID id,
                                            @RequestParam("file") MultipartFile file) {
        eventImageUploader.uploadImage(id, file);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{eventId}/images/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable("eventId") UUID eventId,
                                            @PathVariable("imageId") UUID imageId) {
        eventImageModifier.deleteImage(eventId, imageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}/covers")
    public ResponseEntity<byte[]> getCover(@PathVariable("id") UUID id) {
        final Image cover = eventImageReader.getCover(id);
        final ContentDisposition contentDisposition = ContentDisposition.attachment()
                .filename(cover.name(), StandardCharsets.UTF_8)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .contentType(valueOf(cover.type()))
                .body(cover.content());
    }
}

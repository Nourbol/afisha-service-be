package kz.edu.astanait.afisha.service;

import java.util.UUID;

public interface EventImageModifier {

    void deleteImage(UUID eventId, UUID imageId);
}

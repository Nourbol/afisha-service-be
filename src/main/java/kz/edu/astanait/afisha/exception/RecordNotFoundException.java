package kz.edu.astanait.afisha.exception;

import java.util.UUID;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(final String message) {
        super(message);
    }

    public static RecordNotFoundException eventNotFound(final UUID eventId) {
        return new RecordNotFoundException("Event not found: " + eventId);
    }

    public static RecordNotFoundException categoryNotFound(final UUID categoryId) {
        return new RecordNotFoundException("Category not found: " + categoryId);
    }

    public static RecordNotFoundException userNotFoundByEmail(final String email) {
        return new RecordNotFoundException("User not found by email: " + email);
    }
}

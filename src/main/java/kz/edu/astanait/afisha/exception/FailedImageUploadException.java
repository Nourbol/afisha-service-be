package kz.edu.astanait.afisha.exception;

public class FailedImageUploadException extends RuntimeException {

    public FailedImageUploadException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

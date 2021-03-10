package rrlabs.i18n.config.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException () {}

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

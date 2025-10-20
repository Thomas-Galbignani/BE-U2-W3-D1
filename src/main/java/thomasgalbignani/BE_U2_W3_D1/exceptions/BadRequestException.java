package thomasgalbignani.BE_U2_W3_D1.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
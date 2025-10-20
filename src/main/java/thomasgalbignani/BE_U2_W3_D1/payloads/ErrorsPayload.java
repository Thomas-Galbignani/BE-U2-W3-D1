package thomasgalbignani.BE_U2_W3_D1.payloads;

import java.time.LocalDateTime;


public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;

    public ErrorsPayload(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

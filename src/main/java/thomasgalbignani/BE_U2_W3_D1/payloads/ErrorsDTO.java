package thomasgalbignani.BE_U2_W3_D1.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}
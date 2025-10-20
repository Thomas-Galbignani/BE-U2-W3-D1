package thomasgalbignani.BE_U2_W3_D1.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsWithListDTO(String message,
                                LocalDateTime timestamp,
                                List<String> errorsList) {
}

package thomasgalbignani.BE_U2_W3_D1.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import thomasgalbignani.BE_U2_W3_D1.entities.StatoViaggio;

import java.time.LocalDate;

@Getter
public class ViaggioDTO {
    @NotBlank(message = "La destinazione è obbligatoria")
    private String destinazione;

    @NotNull(message = "La data del viaggio è obbligatoria")
    private LocalDate dataViaggio;

    @NotNull(message = "Lo stato del viaggio è obbligatorio")
    private StatoViaggio stato;
}
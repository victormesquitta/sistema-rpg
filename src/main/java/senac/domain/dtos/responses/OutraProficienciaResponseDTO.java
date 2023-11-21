package senac.domain.dtos.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OutraProficienciaResponseDTO {
    private int codOutraProficiencia;

    @NotBlank(message = "O tipo não pode estar em branco")
    @Size(max = 45, message = "O tipo deve ter no máximo 45 caracteres")
    private String tipo;

    @NotBlank(message = "A proficiência não pode estar em branco")
    @Size(max = 45, message = "A proficiência deve ter no máximo 45 caracteres")
    private String proficiencia;

    @NotNull(message = "O código do personagem não pode ser nulo")
    private Integer codPersonagem;
}

package senac.domain.dtos.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RolagemResponseDTO {

    private Integer codRolagem;

    @NotNull(message = "O personagem não pode ser nulo")
    private Integer codPersonagem;

    @NotNull(message = "O resultado da rolagem não pode ser nulo")
    private Integer resultRolagem;

    @NotBlank(message = "O tipo de dado não pode estar em branco")
    private String tipoDado;

    @NotNull(message = "A data não pode ser nula")
    private LocalDateTime data;

    @NotBlank(message = "O tipo de rolagem não pode estar em branco")
    private String tipoRolagem;

    @NotNull(message = "A quantidade de rolagens não pode ser nula")
    private Integer qtdRolagens;

}

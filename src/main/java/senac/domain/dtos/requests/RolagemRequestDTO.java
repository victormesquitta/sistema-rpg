package senac.domain.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RolagemRequestDTO {

    @NotNull(message = "O personagem não pode ser nulo")
    private Integer codPersonagem;

    @NotBlank(message = "O tipo de dado não pode estar em branco")
    private String tipoDado;

    @NotBlank(message = "O tipo de rolagem não pode estar em branco")
    private String tipoRolagem;

    @NotNull(message = "A quantidade de rolagens não pode ser nula")
    private Integer qtdRolagens;

}

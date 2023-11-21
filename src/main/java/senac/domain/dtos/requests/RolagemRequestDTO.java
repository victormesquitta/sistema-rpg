package senac.domain.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class RolagemRequestDTO {

    @NotNull(message = "O personagem não pode ser nulo")
    private Integer codPersonagem;

    @NotNull(message = "O resultado da rolagem não pode ser nulo")
    private Integer resultRolagem;

    @NotBlank(message = "O tipo de dado não pode estar em branco")
    private String tipoDado;

    @NotNull(message = "A data não pode ser nula")
    private Timestamp data;

    @NotBlank(message = "O tipo de rolagem não pode estar em branco")
    private String tipoRolagem;

    @NotNull(message = "A quantidade de rolagens não pode ser nula")
    private Integer qtdRolagens;

}

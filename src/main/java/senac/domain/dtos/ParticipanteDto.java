package senac.domain.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ParticipanteDto {

    private Integer codParticipante;

    @NotNull(message = "O código do usuário não pode ser nulo")
    private Integer codUsuario;

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O cargo não pode estar em branco")
    private String cargo;

    private boolean adm;


}

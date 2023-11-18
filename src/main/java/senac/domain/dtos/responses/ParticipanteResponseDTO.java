package senac.domain.dtos.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParticipanteResponseDTO {

    @NotNull(message = "O código do usuário não pode ser nulo")
    private Integer codUsuario;

    @NotNull(message = "O campo 'codCampanha' não pode ser nulo")
    private Integer codCampanha;

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O cargo não pode estar em branco")
    private String cargo;

    private boolean adm;
    private boolean admMaster;
}

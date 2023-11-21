package senac.domain.dtos.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProficienciaResponseDTO {

    private int codProficiencia;

    @NotBlank(message = "A ferramenta não pode estar em branco")
    @Size(max = 45, message = "A ferramenta deve ter no máximo 45 caracteres")
    private String ferramenta;

    @NotBlank(message = "O tipo de proficiência não pode estar em branco")
    @Size(max = 45, message = "O tipo de proficiência deve ter no máximo 45 caracteres")
    private String tipoProficiencia;

    @NotBlank(message = "O atributo relacionado não pode estar em branco")
    @Size(max = 45, message = "O atributo relacionado deve ter no máximo 45 caracteres")
    private String atributoRelacionado;

    @Size(max = 45, message = "O modificador deve ter no máximo 45 caracteres")
    private Integer modificador;

    @NotNull(message = "O código do personagem não pode ser nulo")
    private Integer codPersonagem;

}

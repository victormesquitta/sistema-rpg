package senac.domain.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EquipamentoRequestDTO {

    @NotNull(message = "O campo 'codpersonagem' é obrigatório.")
    private Integer codPersonagem;

    @NotBlank(message = "O campo 'nome' é obrigatório.")
    private String nome;

    @NotNull(message = "O campo 'quantidade' é obrigatório.")
    private Integer quantidade;

    @NotNull(message = "O campo 'peso' é obrigatório.")
    private Float peso;
}

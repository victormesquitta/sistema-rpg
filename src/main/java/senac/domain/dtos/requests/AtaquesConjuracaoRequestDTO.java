package senac.domain.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AtaquesConjuracaoRequestDTO {

    @NotNull(message = "O campo 'codPersonagem' não pode ser nulo.")
    private Integer codPersonagem;

    @NotBlank(message = "O campo 'nome' não pode estar em branco.")
    private String nome;

    @NotNull(message = "O campo 'bonusAtaque' não pode ser nulo.")
    private Integer bonusAtaque;

    @NotBlank(message = "O campo 'dano' não pode estar em branco.")
    private String dano;

    @NotBlank(message = "O campo 'tipoDano' não pode estar em branco.")
    private String tipoDano;

    @NotBlank(message = "O campo 'origem' não pode estar em branco.")
    private String origem;

    // Getters e Setters
}

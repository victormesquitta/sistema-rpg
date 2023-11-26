package senac.domain.dtos.responses;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PericiasResponseDTO {

    private Integer codPericias;

    @NotNull(message = "O código do personagem não pode ser nulo")
    private Integer codPersonagem;

    @NotNull(message = "O campo 'atletismo' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'atletismo' é 0")
    private Integer atletismo;

    @NotNull(message = "O campo 'acrobacia' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'acrobacia' é 0")
    private Integer acrobacia;

    @NotNull(message = "O campo 'furtividade' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'furtividade' é 0")
    private Integer furtividade;

    @NotNull(message = "O campo 'prestidigitacao' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'prestidigitacao' é 0")
    private Integer prestidigitacao;

    @NotNull(message = "O campo 'arcanismo' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'arcanismo' é 0")
    private Integer arcanismo;

    @NotNull(message = "O campo 'historia' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'historia' é 0")
    private Integer historia;

    @NotNull(message = "O campo 'investigacao' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'investigacao' é 0")
    private Integer investigacao;

    @NotNull(message = "O campo 'natureza' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'natureza' é 0")
    private Integer natureza;

    @NotNull(message = "O campo 'religiao' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'religiao' é 0")
    private Integer religiao;

    @NotNull(message = "O campo 'intuicao' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'intuicao' é 0")
    private Integer intuicao;

    @NotNull(message = "O campo 'lidarComAnimais' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'lidarComAnimais' é 0")
    private Integer lidarComAnimais;

    @NotNull(message = "O campo 'medicina' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'medicina' é 0")
    private Integer medicina;

    @NotNull(message = "O campo 'percepcao' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'percepcao' é 0")
    private Integer percepcao;

    @NotNull(message = "O campo 'sobrevivencia' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'sobrevivencia' é 0")
    private Integer sobrevivencia;

    @NotNull(message = "O campo 'atuacao' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'atuacao' é 0")
    private Integer atuacao;

    @NotNull(message = "O campo 'enganacao' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'enganacao' é 0")
    private Integer enganacao;

    @NotNull(message = "O campo 'intimidacao' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'intimidacao' é 0")
    private Integer intimidacao;

    @NotNull(message = "O campo 'persuasao' não pode ser nulo")
    @Min(value = 0, message = "O valor mínimo para 'persuasao' é 0")
    private Integer persuasao;
}

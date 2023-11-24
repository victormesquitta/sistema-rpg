package senac.domain.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentoTracoRequestDTO {

    @NotBlank(message = "O nome do talento/traço não pode estar em branco.")
    private String nome;

    @NotBlank(message = "A fonte do talento/traço não pode estar em branco.")
    private String fonte;

    @NotBlank(message = "O tipo de fonte do talento/traço não pode estar em branco.")
    private String tipoFonte;

    @NotBlank(message = "A descrição do talento/traço não pode estar em branco.")
    private String descricao;

    @NotNull(message = "O código do personagem não pode ser nulo.")
    private Integer codPersonagem;
}
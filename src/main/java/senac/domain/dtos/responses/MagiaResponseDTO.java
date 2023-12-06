package senac.domain.dtos.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MagiaResponseDTO {

    @NotNull(message = "O campo 'codMagia' é obrigatório.")
    private Integer codMagia;

    @NotNull(message = "O campo 'codPersonagem' é obrigatório.")
    private Integer codPersonagem;

    @NotBlank(message = "O campo 'material' é obrigatório.")
    @Size(max = 30, message = "O campo 'material' deve ter no máximo {max} caracteres.")
    private String material;

    @NotBlank(message = "O campo 'tempoConjuracao' é obrigatório.")
    @Size(max = 30, message = "O campo 'tempoConjuracao' deve ter no máximo {max} caracteres.")
    private String tempoConjuracao;

    @NotBlank(message = "O campo 'alcance' é obrigatório.")
    @Size(max = 20, message = "O campo 'alcance' deve ter no máximo {max} caracteres.")
    private String alcance;

    @NotBlank(message = "O campo 'componente' é obrigatório.")
    @Size(max = 300, message = "O campo 'componente' deve ter no máximo {max} caracteres.")
    private String componente;

    @NotBlank(message = "O campo 'duracao' é obrigatório.")
    @Size(max = 30, message = "O campo 'duracao' deve ter no máximo {max} caracteres.")
    private String duracao;

    @NotBlank(message = "O campo 'descricao' é obrigatório.")
    @Size(max = 5000, message = "O campo 'descricao' deve ter no máximo {max} caracteres.")
    private String descricao;

    @NotNull(message = "O campo 'nivelMagia' é obrigatório.")
    private Integer nivelMagia;

    @NotBlank(message = "O campo 'tipoMagia' é obrigatório.")
    @Size(max = 15, message = "O campo 'tipoMagia' deve ter no máximo {max} caracteres.")
    private String tipoMagia;
}

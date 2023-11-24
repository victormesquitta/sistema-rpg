package senac.domain.dtos.responses;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtributosResponseDTO {

    private Integer codAtributos;

    @NotNull(message = "O código do personagem não pode ser nulo")
    private Integer codPersonagem;

    @NotNull(message = "A força não pode ser nula")
    private Integer forca;

    @NotNull(message = "A inteligência não pode ser nula")
    private Integer inteligencia;

    @NotNull(message = "A destreza não pode ser nula")
    private Integer destreza;

    @NotNull(message = "A constituição não pode ser nula")
    private Integer constituicao;

    @NotNull(message = "O carisma não pode ser nulo")
    private Integer carisma;

    @NotNull(message = "A sabedoria não pode ser nula")
    private Integer sabedoria;
}
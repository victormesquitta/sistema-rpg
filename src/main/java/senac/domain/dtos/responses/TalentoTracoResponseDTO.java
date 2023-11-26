package senac.domain.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentoTracoResponseDTO {

    private Integer codTalentoTraco;

    private String nome;

    private String fonte;

    private String tipoFonte;

    private String descricao;

    private Integer codPersonagem;
}
package senac.domain.dtos.responses;

import lombok.Data;

@Data
public class AtaquesConjuracaoResponseDTO {

    private Integer codAtaquesConjuracao;
    private Integer codPersonagem;
    private String nome;
    private Integer bonusAtaque;
    private String dano;
    private String tipoDano;
    private String origem;
}

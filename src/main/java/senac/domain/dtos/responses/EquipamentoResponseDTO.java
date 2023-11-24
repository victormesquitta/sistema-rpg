package senac.domain.dtos.responses;

import lombok.Data;

@Data
public class EquipamentoResponseDTO {

    private Integer codEquipamento;
    private Integer codPersonagem;
    private String nome;
    private Integer quantidade;
    private Float peso;
}

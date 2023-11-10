package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_equipamento")
public class EquipamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codequipamento")
    private Integer codEquipamento;

    @ManyToOne
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_equipamento_personagem1"))
    private PersonagemModel personagemModel;

    @Column(name = "nome", length = 100, nullable = false   )
    private String nome;

    @Column(name = "qtd", nullable = false)
    private Integer quantidade;

    @Column(name = "peso", nullable = false)
    private Float peso;
}

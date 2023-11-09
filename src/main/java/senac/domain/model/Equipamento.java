package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codequipamento")
    private Integer codEquipamento;

    @ManyToOne
    @JoinColumn(name = "personagem_codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_equipamentos_personagem1"))
    private Personagem personagem;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "qtd")
    private Integer quantidade;

    @Column(name = "peso")
    private Float peso;
}

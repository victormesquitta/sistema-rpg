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
@Table(name="tb_equipamento")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codequipamento")
    private Integer codEquipamento;

    @ManyToOne
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_equipamento_personagem1"))
    private Personagem personagem;

    @Column(name = "nome", length = 100, nullable = false   )
    private String nome;

    @Column(name = "qtd", nullable = false)
    private Integer quantidade;

    @Column(name = "peso", nullable = false)
    private Float peso;
}

package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_outraproficiencia")
public class OutraProficiencia {
    @Id
    @Column(name = "codoutraproficiencia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private int codOutraProficiencia;

    @Column(name = "tipo", length = 45, columnDefinition = "COMMENT 'idioma, armas, armadura ou outros'", nullable = false)
    private String tipo;

    @Column(name = "proficiencia", length = 45, nullable = false)
    private String proficiencia;

    @ManyToOne
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_outraproficiencia_personagem1"))
    private Personagem personagem;

}

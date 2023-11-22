package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_outraproficiencia")
public class OutraProficienciaModel {
    @Id
    @Column(name = "codoutraproficiencia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codOutraProficiencia;

    @Comment("idioma, armas, armadura ou outros")
    @Column(name = "tipo", length = 45, nullable = false)
    private String tipo;

    @Column(name = "proficiencia", length = 45, nullable = false)
    private String proficiencia;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_outraproficiencia_personagem1"))
    private PersonagemModel personagemModel;

}

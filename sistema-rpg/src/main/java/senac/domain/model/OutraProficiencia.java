package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutraProficiencia {
    @Id
    @Column(name = "codoutraproficiencia")
    private int codOutraProficiencia;

    @Column(name = "tipo", length = 45, columnDefinition = "COMMENT 'idioma, armas, armadura ou outros'")
    private String tipo;

    @Column(name = "proficiencia", length = 45)
    private String proficiencia;

    @ManyToOne
    @JoinColumn(name = "personagem_codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_outrasproficiencias_personagem1"))
    private Personagem personagem;

}

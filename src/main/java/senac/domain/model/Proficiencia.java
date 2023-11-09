package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proficiencia {

    @Id
    @Column(name = "codproficiencia")
    private int codProficiencia;

    @Column(name = "ferramenta", length = 45)
    private String ferramenta;

    @Column(name = "proficiencia", length = 45)
    private String tipoProficiencia;

    @Column(name = "atributo", length = 45)
    private String atributoRelacionado;

    @Column(name = "modificador", length = 45)
    private String modificador;

    @ManyToOne
    @JoinColumn(name = "personagem_codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_proficiencias_personagem1"))
    private Personagem personagem;
}

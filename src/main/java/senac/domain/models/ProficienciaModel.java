package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_proficiencia")
public class ProficienciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codproficiencia")
    private int codProficiencia;

    @ManyToOne
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_proficiencia_personagem1"))
    private PersonagemModel personagemModel;

    @Column(name = "ferramenta", length = 45, nullable = false)
    private String ferramenta;

    @Column(name = "tipoproficiencia", length = 45, nullable = false)
    private String tipoProficiencia;

    @Column(name = "atributorelacionado", length = 45, nullable = false)
    private String atributoRelacionado;

    @Column(name = "modificador")
    private Integer modificador;

    @Column(name = "valortotproficiencia")
    private Integer valorTotProficiencia;

}

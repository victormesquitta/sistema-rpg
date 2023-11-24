package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_atributos")
public class AtributosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codatributos")
    private Integer codAtributos;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_atributos_personagem1"))
    private PersonagemModel personagemModel;

    @Column(name = "forca", nullable = false)
    private Integer forca;

    @Column(name = "inteligencia", nullable = false)
    private Integer inteligencia;

    @Column(name = "destreza", nullable = false)
    private Integer destreza;

    @Column(name = "constituicao", nullable = false)
    private Integer constituicao;

    @Column(name = "carisma", nullable = false)
    private Integer carisma;

    @Column(name = "sabedoria", nullable = false)
    private Integer sabedoria;
}

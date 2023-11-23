package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_pericias")
public class PericiasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codpericia")
    private Integer codPericia;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_pericia_personagem1"))
    private PersonagemModel personagemModel;

    @Column(name = "atletismo", length = 45, nullable = false)
    private Integer atletismo;

    @Column(name = "acrobacia", length = 45, nullable = false)
    private Integer acrobacia;

    @Column(name = "furtividade", length = 45, nullable = false)
    private Integer furtividade;

    @Column(name = "prestidigitacao", length = 45, nullable = false)
    private Integer prestidigitacao;

    @Column(name = "arcanismo", length = 45, nullable = false)
    private Integer arcanismo;

    @Column(name = "historia", length = 45, nullable = false)
    private Integer historia;

    @Column(name = "investigacao", length = 45, nullable = false)
    private Integer investigacao;

    @Column(name = "natureza", length = 45, nullable = false)
    private Integer natureza;

    @Column(name = "religiao", length = 45, nullable = false)
    private Integer religiao;

    @Column(name = "intuicao", length = 45, nullable = false)
    private Integer intuicao;

    @Column(name = "lidarcomanimais", length = 45, nullable = false)
    private Integer lidarComAnimais;

    @Column(name = "medicina", length = 45, nullable = false)
    private Integer medicina;

    @Column(name = "percepcao", length = 45, nullable = false)
    private Integer percepcao;

    @Column(name = "sobrevivencia", length = 45, nullable = false)
    private Integer sobrevivencia;

    @Column(name = "atuacao", length = 45, nullable = false)
    private Integer atuacao;

    @Column(name = "enganacao", length = 45, nullable = false)
    private Integer enganacao;

    @Column(name = "intimidacao", length = 45, nullable = false)
    private Integer intimidacao;

    @Column(name = "persuasao", length = 45, nullable = false)
    private Integer persuasao;
}

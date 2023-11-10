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
@Table(name="tb_pericias")
public class Pericias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codpericia")
    private Integer codPericia;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem")
    private Personagem personagem;

    @Column(name = "atletismo", length = 45, nullable = false)
    private String atletismo;

    @Column(name = "acrobacia", length = 45, nullable = false)
    private String acrobacia;

    @Column(name = "furtividade", length = 45, nullable = false)
    private String furtividade;

    @Column(name = "prestidigitacao", length = 45, nullable = false)
    private String prestidigitacao;

    @Column(name = "arcanismo", length = 45, nullable = false)
    private String arcanismo;

    @Column(name = "historia", length = 45, nullable = false)
    private String historia;

    @Column(name = "investigacao", length = 45, nullable = false)
    private String investigacao;

    @Column(name = "natureza", length = 45, nullable = false)
    private String natureza;

    @Column(name = "religiao", length = 45, nullable = false)
    private String religiao;

    @Column(name = "intuicao", length = 45, nullable = false)
    private String intuicao;

    @Column(name = "lidarcomanimais", length = 45, nullable = false)
    private String lidarComAnimais;

    @Column(name = "medicina", length = 45, nullable = false)
    private String medicina;

    @Column(name = "percepcao", length = 45, nullable = false)
    private String percepcao;

    @Column(name = "sobrevivencia", length = 45, nullable = false)
    private String sobrevivencia;

    @Column(name = "atuacao", length = 45, nullable = false)
    private String atuacao;

    @Column(name = "enganacao", length = 45, nullable = false)
    private String enganacao;

    @Column(name = "intimidacao", length = 45, nullable = false)
    private String intimidacao;

    @Column(name = "persuasao", length = 45, nullable = false)
    private String persuasao;
}

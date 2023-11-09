package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pericias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codpericia")
    private Integer codPericia;

    @Column(name = "atletismo", length = 45)
    private String atletismo;

    @Column(name = "acrobacia", length = 45)
    private String acrobacia;

    @Column(name = "furtividade", length = 45)
    private String furtividade;

    @Column(name = "prestidigitacao", length = 45)
    private String prestidigitacao;

    @Column(name = "arcanismo", length = 45)
    private String arcanismo;

    @Column(name = "historia", length = 45)
    private String historia;

    @Column(name = "investigacao", length = 45)
    private String investigacao;

    @Column(name = "natureza", length = 45)
    private String natureza;

    @Column(name = "religiao", length = 45)
    private String religiao;

    @Column(name = "intuicao", length = 45)
    private String intuicao;

    @Column(name = "lidarcomanimais", length = 45)
    private String lidarComAnimais;

    @Column(name = "medicina", length = 45)
    private String medicina;

    @Column(name = "percepcao", length = 45)
    private String percepcao;

    @Column(name = "sobrevivencia", length = 45)
    private String sobrevivencia;

    @Column(name = "atuacao", length = 45)
    private String atuacao;

    @Column(name = "enganacao", length = 45)
    private String enganacao;

    @Column(name = "intimidacao", length = 45)
    private String intimidacao;

    @Column(name = "persuasao", length = 45)
    private String persuasao;
}

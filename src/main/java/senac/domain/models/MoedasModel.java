package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_moedas")
public class MoedasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codMoeda")
    private Integer codMoeda;

    @Column(name = "po", columnDefinition = "INT COMMENT 'peças de ouro'", nullable = false)
    private String po;

    @Column(name = "pp", columnDefinition = "INT COMMENT 'peças de prata'", nullable = false)
    private String pp;

    @Column(name = "pc", columnDefinition = "INT COMMENT 'peças de cobre'", nullable = false)
    private String pc;

    @Column(name = "pl", columnDefinition = "INT COMMENT 'peças de platina'", nullable = false)
    private Integer pl;

    @Column(name = "da", columnDefinition = "INT COMMENT 'diamante astral'", nullable = false)
    private String da;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem")
    private PersonagemModel personagemModel;

}

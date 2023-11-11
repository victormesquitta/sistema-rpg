package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_moedas")
public class MoedasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codMoeda")
    private Integer codMoeda;

    @Column(name = "po", columnDefinition = "INT COMMENT 'peças de ouro'", nullable = false)
    private Integer po;

    @Column(name = "pp", columnDefinition = "INT COMMENT 'peças de prata'", nullable = false)
    private Integer pp;

    @Column(name = "pc", columnDefinition = "INT COMMENT 'peças de cobre'", nullable = false)
    private Integer pc;

    @Column(name = "pl", columnDefinition = "INT COMMENT 'peças de platina'", nullable = false)
    private Integer pl;

    @Column(name = "da", columnDefinition = "INT COMMENT 'diamante astral'", nullable = false)
    private Integer da;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem")
    private PersonagemModel personagemModel;

}

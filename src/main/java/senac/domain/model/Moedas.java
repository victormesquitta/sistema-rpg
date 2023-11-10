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
@Table(name="tb_moedas")
public class Moedas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codMoeda")
    private Integer codMoeda;

    @Column(name = "po", length = 45, columnDefinition = "VARCHAR(45) COMMENT 'peças de ouro'", nullable = false)
    private String po;

    @Column(name = "pp", length = 45, columnDefinition = "VARCHAR(45) COMMENT 'peças de prata'", nullable = false)
    private String pp;

    @Column(name = "pc", length = 45, columnDefinition = "VARCHAR(45) COMMENT 'peças de cobre'", nullable = false)
    private String pc;

    @Column(name = "pl", columnDefinition = "INT COMMENT 'peças de platina'", nullable = false)
    private Integer pl;

    @Column(name = "da", length = 45, columnDefinition = "VARCHAR(45) COMMENT 'diamante astral'", nullable = false)
    private String da;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem")
    private Personagem personagem;

}

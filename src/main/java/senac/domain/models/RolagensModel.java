package senac.domain.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_rolagens")
public class RolagensModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codrolagem")
    private Integer codrolagem;

    @ManyToOne
    @JoinColumn(name = "codcampanha", referencedColumnName = "codcampanha",
            foreignKey = @ForeignKey(name = "fk_rolagens_campanhas1"))
    private CampanhaModel campanhaModel;

    @ManyToOne
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_rolagens_personagem1"))
    private PersonagemModel personagemModel;

    @Column(name = "resultrolagem", nullable = false)
    private Integer resultrolagem;

    @Column(name = "tipodado", nullable = false)
    private String tipodado ;

    @Column(name = "data", nullable = false)
    private Timestamp data ;

    @Column(name = "tiporolagem", nullable = false)
    private String tiporolagem ;

}

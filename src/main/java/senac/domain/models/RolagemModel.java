package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_rolagem")
public class RolagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codrolagem")
    private Integer codRolagem;

    @ManyToOne
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_rolagem_personagem1"))
    private PersonagemModel personagemModel;

    @Column(name = "resultrolagem", nullable = false)
    private Integer resultRolagem;

    @Column(name = "tipodado", nullable = false)
    private String tipoDado ;

    @Column(name = "data", nullable = false)
    private LocalDateTime data ;

    @Column(name = "tiporolagem", nullable = false)
    private String tipoRolagem ;

    @Column(name = "qtdrolagens", nullable = false)
    private Integer qtdRolagens;

    @Column(name = "valoresgerados", length = 100)
    private String valoresGerados;

}

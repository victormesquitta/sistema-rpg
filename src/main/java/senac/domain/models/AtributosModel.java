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
@Table(name="tb_atributos")
public class AtributosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codatributo")
    private Integer codAtributo;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem")
    private PersonagemModel personagemModel;

    @Column(name = "forca", length = 45, nullable = false)
    private String forca;

    @Column(name = "inteligencia", length = 45, nullable = false)
    private String inteligencia;

    @Column(name = "destreza", length = 45, nullable = false)
    private String destreza;

    @Column(name = "constituicao", length = 45, nullable = false)
    private String constituicao;

    @Column(name = "carisma", length = 45, nullable = false)
    private String carisma;

    @Column(name = "sabedoria", length = 45, nullable = false)
    private String sabedoria;
}

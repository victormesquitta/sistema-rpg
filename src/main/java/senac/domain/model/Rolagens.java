package senac.domain.model;
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
@Table(name = "rolagens")
public class Rolagens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Integer codrolagem;

    @ManyToOne
    @JoinColumn(name = "campanha_codcampanha")
    private Campanha campanha;
    @ManyToOne

    @JoinColumn(name = "personagem_codpersonagem")
    private Personagem personagem;

    @Column(name = "resultrolagem")
    private Integer resultrolagem;

    @Column(name = "tipodado")
    private String tipodado ;

    @Column(name = "data")
    private Timestamp data ;

    @Column(name = "tiporolagem")
    private String tiporolagem ;

}

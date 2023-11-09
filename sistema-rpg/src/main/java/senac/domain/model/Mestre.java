package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mestre")
public class Mestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Integer codmestre;

    @ManyToOne
    @JoinColumn(name = "participante_codparticipante")
    private Participante participante;

}

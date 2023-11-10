package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_mestre")
public class MestreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codmestre")
    private Integer codmestre;

    @ManyToOne
    @JoinColumn(name = "codparticipante", referencedColumnName = "codparticipante",
            foreignKey = @ForeignKey(name = "fk_mestre_participante1"))
    private ParticipanteModel participanteModel;

}

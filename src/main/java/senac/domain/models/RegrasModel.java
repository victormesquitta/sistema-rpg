package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_regras")
public class RegrasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codregras")
    private Integer codRegras;

    // conferir se o tipo está certo para a conversão
    @Column(name = "data")
    private LocalDate data;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 2000)
    private String descricao;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codparticipante", referencedColumnName = "codparticipante",
            foreignKey = @ForeignKey(name = "fk_regras_participante1"))
    private ParticipanteModel participanteModel;
}

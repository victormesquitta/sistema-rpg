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
@Table(name="tb_regras")
public class Regras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codregras")
    private Integer codregras;

    // conferir se o tipo está certo para a conversão
    @Column(name = "data")
    private Timestamp data;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "nome", nullable = false, length = 2000)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "codcampanha", referencedColumnName = "codcampanha",
            foreignKey = @ForeignKey(name = "fk_regras_campanha1"))
    private Campanha campanha;

    @ManyToOne
    @JoinColumn(name = "codmestre", referencedColumnName = "codmestre",
            foreignKey = @ForeignKey(name = "fk_regras_mestre1"))
    private Mestre mestre;

}

package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_regras")
public class RegrasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codregras")
    private Integer codregras;

    // conferir se o tipo está certo para a conversão
    @Column(name = "data")
    private Timestamp data;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 2000)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "codcampanha", referencedColumnName = "codcampanha",
            foreignKey = @ForeignKey(name = "fk_regras_campanha1"))
    private CampanhaModel campanhaModel;

    @ManyToOne
    @JoinColumn(name = "codmestre", referencedColumnName = "codmestre",
            foreignKey = @ForeignKey(name = "fk_regras_mestre1"))
    private MestreModel mestreModel;

}
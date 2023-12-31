package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_magia")
public class MagiaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codmagia")
    private Integer codMagia;

    @ManyToOne
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_magia_personagem1"))
    private PersonagemModel personagemModel;

    @Column(name = "material", length = 200)
    private String material;

    @Column(name = "tempoconjuracao", length = 30, nullable = false)
    private String tempoConjuracao;

    @Column(name = "alcance", length = 20, nullable = false)
    private String alcance;

    @Column(name = "componente", length = 300, nullable = false)
    private String componente;

    @Column(name = "duracao", length = 30, nullable = false)
    private String duracao;

    @Column(name = "descricao", length = 5000, nullable = false)
    private String descricao;

    @Column(name = "nvlmagia", nullable = false)
    private Integer nivelMagia;

    @Column(name = "tipomagia", length = 15, nullable = false)
    private String tipoMagia;

}

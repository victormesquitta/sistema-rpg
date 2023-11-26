package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_ataquesconjuracao")
public class AtaquesConjuracaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codataquesconjuracao")
    private Integer codAtaquesConjuracao;

    @ManyToOne
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_ataquesconjuracao_personagem1"))
    private PersonagemModel personagemModel;

    @Column(name = "nome", length = 300, nullable = false)
    private String nome;

    @Column(name = "bonusataque", nullable = false)
    private Integer bonusAtaque;

    // passar o dado a ser rolado. ex: 3d20
    @Column(name = "tipodado")
    private String tipoDado;

    @Column(name = "qtdrolagens")
    private Integer qtdRolagens;

    @Column(name = "tipodano", length = 45, nullable = false)
    private String tipoDano;

    @Column(name = "origem", length = 45, nullable = false)
    private String origem; // se é ataque ou conjuração
}

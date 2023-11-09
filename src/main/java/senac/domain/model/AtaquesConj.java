package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AtaquesConj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codataquesconj")
    private Integer codAtaquesConj;

    @Column(name = "nome", length = 300)
    private String nome;

    @Column(name = "bonusataque")
    private Integer bonusAtaque;

    @Column(name = "dano")
    private Integer dano;

    @Column(name = "tipodano", length = 45)
    private String tipoDano;

    @Column(name = "origem", length = 45)
    private String origem; // se é ataque ou conjuração

    @ManyToOne
    @JoinColumn(name = "equipamentos_codequipamento", referencedColumnName = "codequipamento",
            foreignKey = @ForeignKey(name = "fk_ataquesconj_equipamentos1"))
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "magias_codmagia", referencedColumnName = "codmagia",
            foreignKey = @ForeignKey(name = "fk_ataquesconj_magias1"))
    private Magia magia;
}

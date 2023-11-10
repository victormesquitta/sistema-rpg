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
@Table(name="tb_ataquesconj")
public class AtaquesConj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codataquesconj")
    private Integer codAtaquesConj;

    @Column(name = "nome", length = 300, nullable = false)
    private String nome;

    @Column(name = "bonusataque", nullable = false)
    private Integer bonusAtaque;

    // passar o dado a ser rolado. ex: 3d20
    @Column(name = "dano", length = 20, nullable = false)
    private String dano;

    @Column(name = "tipodano", length = 45, nullable = false)
    private String tipoDano;

    @Column(name = "origem", length = 45, nullable = false)
    private String origem; // se é ataque ou conjuração

    @ManyToOne
    @JoinColumn(name = "codequipamento", referencedColumnName = "codequipamento",
            foreignKey = @ForeignKey(name = "fk_ataquesconj_equipamento1"))
    private Equipamento equipamento;

    @ManyToOne
    @JoinColumn(name = "codmagia", referencedColumnName = "codmagia",
            foreignKey = @ForeignKey(name = "fk_ataquesconj_magias1"))
    private Magia magia;
}

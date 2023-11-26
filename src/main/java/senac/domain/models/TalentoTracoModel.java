package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_talentotraco")
public class TalentoTracoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codtalentotraco")
    private Integer codTalentoTraco;

    @Column(name = "nome", length = 45)
    private String nome;

    @Column(name = "fonte", length = 45)
    private String fonte;

    @Column(name = "tipofonte", length = 45)
    private String tipoFonte;

    @Column(name = "descricao", length = 1000)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "codpersonagem", referencedColumnName = "codpersonagem",
            foreignKey = @ForeignKey(name = "fk_talentotraco_personagem1"))
    private PersonagemModel personagemModel;
}
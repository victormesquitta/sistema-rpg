package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_personagem")
public class PersonagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codpersonagem")
    private Integer codPersonagem;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codparticipante", referencedColumnName = "codparticipante",
            foreignKey = @ForeignKey(name = "fk_personagem_participante1"))
    private ParticipanteModel participanteModel;

    @OneToMany(mappedBy = "personagemModel", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ProficienciaModel> proficiencias = new ArrayList<>();

    @OneToMany(mappedBy = "personagemModel", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<OutraProficienciaModel> outrasProficiencias = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "personagemModel", cascade = CascadeType.ALL)
    private List<RolagemModel> rolagens = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "personagemModel", cascade = CascadeType.ALL)
    private List<PericiasModel> pericias = new ArrayList<>();

    @Column(name = "nome")
    private String nome;

    @Lob
    @Column(name = "foto", columnDefinition = "LONGBLOB")
    private byte[] foto;

    @Column(name = "classe", length = 20)
    private String classe;

    @Column(name = "raca", length = 20)
    private String raca;

    @Column(name = "antecedente", length = 20)
    private String antecedente;

    @Column(name = "nivel")
    private Integer nivel;

    @Column(name = "xp")
    private Integer xp;

    @Column(name = "bonusproficiencia")
    private Integer bonusProficiencia;

    @Column(name = "inspiracao", length = 45)
    private String inspiracao;

    @Column(name = "sabedoria_passiva", length = 45)
    private String sabedoriaPassiva;

    @Column(name = "ca")
    private Integer ca;

    @Column(name = "iniciativa")
    private Integer iniciativa;

    @Column(name = "deslocamento")
    private Float deslocamento;

    @Column(name = "hp")
    private Integer hp;

    @Column(name = "hptemp")
    private Integer hpTemp;

    @Column(name = "tracospersonalidade", length = 200)
    private String tracosPersonalidade;

    @Column(name = "ideais", length = 200)
    private String ideais;

    @Column(name = "vinculos", length = 200)
    private String vinculos;

    @Column(name = "fraquezas", length = 200)
    private String fraquezas;

    @Column(name = "corolhos", length = 20)
    private String corOlhos;

    @Column(name = "altura", length = 20)
    private String altura;

    @Column(name = "peso", length = 20)
    private String peso;

    @Column(name = "pele", length = 20)
    private String pele;

    @Column(name = "cabelos", length = 30)
    private String cabelos;

    @Column(name = "aparencia", length = 200)
    private String aparencia;

    @Column(name = "aliadosorg", length = 2000)
    private String aliadosOrg;

    @Column(name = "outrascarac", length = 1000)
    private String outrasCaracteristicas;

    @Column(name = "historia", length = 10000)
    private String historia;

    @Column(name = "tesouro", length = 1000)
    private String tesouro;

    // inteligencia, destreza, ...
    @Column(name = "habilidadeconjuracao", length = 15)
    private String habilidadeConjuracao;

    @Column(name = "cdresistmagia")
    private Integer cdResistMagia;

    @Column(name = "bonusataquemagia")
    private Integer bonusAtaqueMagia;
}

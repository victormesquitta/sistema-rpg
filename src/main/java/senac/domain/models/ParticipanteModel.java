package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_participante")
public class ParticipanteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codparticipante")
    private Integer codParticipante;

//    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
//    @PrimaryKeyJoinColumn(name = "codusuario", referencedColumnName = "codusuario")
    @JoinColumn(name = "codusuario", referencedColumnName = "codusuario",
            foreignKey = @ForeignKey(name = "fk_participante_usuario1"))
    private UsuarioModel usuarioModel;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codcampanha", referencedColumnName = "codcampanha",
            foreignKey = @ForeignKey(name = "fk_participante_campanha1"))
    private CampanhaModel campanhaModel;

    // pegar imagem padrão no do envio da foto ser nulo
//    @Lob
//    @Column(nullable = true, columnDefinition = "LONGBLOB")
//    private Byte[] imagem;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 30)
    private String cargo;

    @Column(nullable = false)
    private boolean adm;

    @Column(name = "admmaster", nullable = false)
    private boolean admMaster;

    @ToString.Exclude
    @OneToMany(mappedBy = "participanteModel", cascade = CascadeType.ALL)
    private List<PersonagemModel> personagens = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "participanteModel", cascade = CascadeType.ALL)
    private List<RegrasModel> regras = new ArrayList<>();

    //ver como ficaria a questão da foto
}

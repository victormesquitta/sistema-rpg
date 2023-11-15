package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne
//    @PrimaryKeyJoinColumn(name = "codusuario", referencedColumnName = "codusuario")
    @JoinColumn(name = "codusuario", referencedColumnName = "codusuario")
    private UsuarioModel usuarioModel;

    @ManyToOne
    @JoinColumn(name = "codcampanha", referencedColumnName = "codcampanha")
    private CampanhaModel campanhaModel;

    // pegar imagem padrão no do envio da foto ser nulo
//    @Lob
//    @Column(nullable = true)
//    private Byte[] imagem;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 30)
    private String cargo;

    @Column(nullable = false)
    private boolean adm;

    //ver como ficaria a questão da foto
}

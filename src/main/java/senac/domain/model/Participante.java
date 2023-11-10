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
@Table(name="tb_participante")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codparticipante")
    private Integer codparticipante;

    @ManyToOne
    @JoinColumn(name = "codusuario", referencedColumnName = "codusuario",
            foreignKey = @ForeignKey(name = "fk_participante_usuario1"))
    private Usuario usuario;

    // pegar imagem padrão no do envio da foto ser nulo
    @Lob
    @Column(nullable = true)
    private Byte[] imagem;

    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    @Column(nullable = false, length = 30)
    private String cargo;

    @Column(nullable = false)
    private boolean Adm;

    //ver como ficaria a questão da foto
}

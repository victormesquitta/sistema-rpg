package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_usuario")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codusuario")
    private Integer codUsuario;

    @Column(name="usuario", nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(name="email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name="senha", nullable = false, length = 30)
    private String senha;

    @Column(name="datacriacao", nullable = false)
    private LocalDate dataCriacao;

//    private Blob imagem;
    @Column(name="horasjogadas", nullable = false)
    private LocalTime horasJogadas;

    @OneToMany(mappedBy = "usuarioModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipanteModel> participantes = new ArrayList<>();
}

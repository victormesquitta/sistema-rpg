package senac.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(name="usuario", nullable = false, unique = true)
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
}

package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codusuario")
    private Integer codusuario;

    @Column(name="usuario", nullable = false, unique = true)
    private String usuario;

    @Column(name="email",nullable = false, unique = true, length = 300)
    private String email;

    @Column(name="senha",nullable = false, length = 30)
    private String senha;

    @Column(name="datacriacao", nullable = false)
    private Date datacriacao;

//    private Blob imagem;
    @Column(name="horasjogadas", nullable = false)
    private Time horasjogadas;
}

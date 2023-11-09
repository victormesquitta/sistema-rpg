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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Integer codusuario;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false, unique = true, length = 300)
    private String email;

    @Column(nullable = false, length = 30)
    private String senha;

    private Date datacriacao;

//    private Blob imagem;

    private Time horasjogadas;
}

package senac.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "codcampanha")
    private Integer codCampanha;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "qtdplayers", nullable = false)
    private Integer qtdPlayers;

    //@Column(name = "imagem", nullable = false, length = 40)
    //private Blob imagem

    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "players", nullable = false)
    private Integer players;

    @Column(name = "senha", nullable = false, length = 40)
    private String senha;

    @Column(name = "qtdplayersonline", nullable = false)
    private Integer qtdPlayersOnline;

    @Column(name = "qtdplayersoffline", nullable = false)
    private Integer qtdPlayersOffline;
}

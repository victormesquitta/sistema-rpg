package senac.domain.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_campanha")
public class CampanhaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codcampanha")
    private Integer codCampanha;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "qtdplayers", nullable = false)
    private Integer qtdPlayers;

    //@Column(name = "imagem", columnDefinition = "BLOB")
    //private byte[] imagem;

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
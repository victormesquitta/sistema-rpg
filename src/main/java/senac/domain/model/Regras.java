package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "regras")
public class Regras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Integer codregras;

    @Column(name = "data")
    private Timestamp data;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "campahas_codcampanha")
    private Campanha campanha;

    @ManyToOne
    @JoinColumn(name = "mestre_codmestre")
    private Mestre mestre;

}

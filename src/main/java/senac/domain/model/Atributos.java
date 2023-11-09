package senac.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Atributos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codatributo")
    private Integer codAtributo;

    @Column(name = "forca", length = 45)
    private String forca;

    @Column(name = "inteligencia", length = 45)
    private String inteligencia;

    @Column(name = "destreza", length = 45)
    private String destreza;

    @Column(name = "constituicao", length = 45)
    private String constituicao;

    @Column(name = "carisma", length = 45)
    private String carisma;

    @Column(name = "sabedoria", length = 45)
    private String sabedoria;
}

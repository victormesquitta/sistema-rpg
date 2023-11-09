package senac.domain.funcionalidades.funcionalidade_personagem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class Ficha_Personagem {

    private String nome;
    private Classe classe;
    private Raca raca;
    private Nivel nivel;

}

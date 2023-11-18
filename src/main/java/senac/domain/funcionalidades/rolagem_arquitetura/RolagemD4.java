package senac.domain.funcionalidades.rolagem_arquitetura;

public class RolagemD4 implements RolagemDadosStrategy {
    @Override
    public int rolar() {
        // Lógica para rolar um dado de 4 lados (D4)
        return (int) (Math.random() * 4) + 1;
    }
}

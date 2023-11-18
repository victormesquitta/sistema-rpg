package senac.domain.funcionalidades.rolagem_arquitetura;

public class RolagemD6 implements RolagemDadosStrategy{
    @Override
    public int rolar() {
        // Lógica para rolar um dado de 6 lados (D6)
        return (int) (Math.random() * 6) + 1;
    }
}

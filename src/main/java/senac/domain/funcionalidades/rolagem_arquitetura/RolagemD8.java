package senac.domain.funcionalidades.rolagem_arquitetura;

public class RolagemD8 implements RolagemDadosStrategy{
    @Override
    public int rolar() {
        // Lógica para rolar um dado de 8 lados (D8)
        return (int) (Math.random() * 8) + 1;
    }
}

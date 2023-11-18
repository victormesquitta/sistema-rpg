package senac.domain.funcionalidades.rolagem_arquitetura;

public class RolagemD20 implements RolagemDadosStrategy{
    @Override
    public int rolar() {
        // Lógica para rolar um dado de 20 lados (D20)
        return (int) (Math.random() * 20) + 1;
    }
}

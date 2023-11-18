package senac.domain.funcionalidades.rolagem_arquitetura;

public class RolagemD12 implements RolagemDadosStrategy{
    @Override
    public int rolar() {
        // Lógica para rolar um dado de 12 lados (D12)
        return (int) (Math.random() * 12) + 1;
    }
}

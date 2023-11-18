package senac.domain.funcionalidades.rolagem_arquitetura;

public class LancadorDadosContext {
    private RolagemDadosStrategy estrategia;

    public void setEstrategia(RolagemDadosStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public int rolarDados() {
        if (estrategia == null) {
            throw new IllegalStateException("Estratégia não definida. Configure uma estratégia antes de rolar dados.");
        }
        return estrategia.rolar();
    }
}
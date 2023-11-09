package senac.domain.funcionalidades.funcionalidade_dado;

public enum TipoDado {

    D4(4), D6(6), D8(8), D12(12), D20(20), D100(100);
    private final int lados;

    TipoDado(int lados) {
        this.lados = lados;
    }

    public int getLados() {
        return lados;
    }
}

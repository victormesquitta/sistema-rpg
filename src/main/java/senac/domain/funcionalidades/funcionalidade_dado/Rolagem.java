package senac.domain.funcionalidades.funcionalidade_dado;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Rolagem {

    private int valorTotRolagem;
    private ArrayList<Integer> valoresDados;
//    private final LogRolagens log = new LogRolagens();

    public Rolagem(int qtd, TipoDado dado) {

        ArrayList<Integer> valoresDados = new ArrayList<>();
        int valorTotRolagem = 0;

        for (int i = 0; i < qtd; i++) {
            int numeroRandomizado = (int) (Math.random() * dado.getLados()) + 1;
            valoresDados.add(numeroRandomizado);
            valorTotRolagem += numeroRandomizado;
        }

//        for (Integer valor : valoresDados) {
//            valorTotRolagem += valor;
//        }

//        log.salvarRolagem(salvarValores(valorTotRolagem, valoresDados));

        System.out.printf("Rolagem com Sucesso!!!\n");
    }

    private Rolagem salvarValores(int valorTotRolagem, ArrayList<Integer> valoresDados) {
        this.valorTotRolagem = valorTotRolagem;
        this.valoresDados = valoresDados;
        return this;
    }

    public int getValorTotRolagem() {
        return valorTotRolagem;
    }

    public void setValorTotRolagem(int valorTotRolagem) {
        this.valorTotRolagem = valorTotRolagem;
    }

    public ArrayList<Integer> getValoresDados() {
        return valoresDados;
    }

    public void setValoresDados(ArrayList<Integer> valoresDados) {
        this.valoresDados = valoresDados;
    }
    // mostrar rolagem atual

    @Override
    public String toString() {
        return "Rolagem{" + "valorTotRolagem=" + valorTotRolagem + ", valoresDados=" + valoresDados + '}';
    }

    public String getTotEValores(){
        return "(" + valorTotRolagem + ") " + this.getValoresDados();
    }
}

package senac.domain.funcionalidades.rolagem_arquitetura;


public class Main {
    public static void main(String[] args) {
        // Criar o contexto
        LancadorDadosContext rolador = new LancadorDadosContext();

        // Definir a estratégia desejada (por exemplo, D4)
        rolador.setEstrategia(new RolagemD4());

        // Rolar dados usando a estratégia configurada
        int resultado = rolador.rolarDados();
        System.out.println("Resultado do dado D4: " + resultado);

        // Alterar a estratégia (por exemplo, para D6)
        rolador.setEstrategia(new RolagemD6());

        // Rolar dados usando a nova estratégia
        resultado = rolador.rolarDados();
        System.out.println("Resultado do dado D6: " + resultado);
    }
}

package senac.domain.funcionalidades.funcionalidade_dado;

public class Main {
    public static void main(String[] args) {


        /*
        *  Na interface visual, vai ter uma área/botão para cada tipo de dado
        *  Terá os seguintes tipos de dados: D4, D6, D8, D12, D20, D100 e Dado Especial
        *  O jogador precisará apenas informar
        *  Precisamos destacar os casos de 20 e 1 natural!!!!!
        */



//        TipoDado D4 = TipoDado.D4;
//        System.out.println(D4.getLados());

        LancadorDeDados roladorLaEle = new LancadorDeDados();

        roladorLaEle.rolagemDeDados(10, TipoDado.D4);
        roladorLaEle.rolagemDeDados(3, TipoDado.D6);
        roladorLaEle.rolagemDeDados(6, TipoDado.D20);
        roladorLaEle.rolagemDeDados(4, TipoDado.D100);
//        roladorLaEle.imprimirUltimaRolagem();
//        roladorLaEle.imprimirLog();

//        roladorLaEle.rolagemDeDados(3, TipoDado.D100);
//        roladorLaEle.mostrarRolagem();

//        roladorLaEle.rolagemDeDados(2, TipoDado.D6);










//        Dado d4 = new D4();
//        System.out.println("3D4: " + d4.rolagemDeDados(3));
//
//        Dado d6 = new D6();
//        System.out.println("3D6: " + d6.rolagemDeDados(3));
//
//        Dado d8 = new D8();
//        System.out.println("3D8: " + d8.rolagemDeDados(3));
//
//        Dado d12 = new D12();
//        System.out.println("3D12: " + d12.rolagemDeDados(3));
//
//        Dado d20 = new D20();
//        System.out.println("3D20: " + d20.rolagemDeDados(3));
//
//        Dado d100 = new D100();
//        System.out.println("3D100: " + d100.rolagemDeDados(3));



    }
}

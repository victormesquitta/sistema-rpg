package senac.domain.funcionalidades.funcionalidade_dado;

import lombok.Data;

import java.util.ArrayList;

@Data
public class LogRolagens {

    private ArrayList<Rolagem> todasRolagens;
    private ArrayList<Integer> somaRolagens;

    public LogRolagens() {
        this.todasRolagens = new ArrayList<>();
        this.somaRolagens = new ArrayList<>();
    }

    // invocar ele em toda a rolagem de dados

    public void salvarRolagem(Rolagem rolagem){
        todasRolagens.add(rolagem);
        somaRolagens.add(rolagem.getValorTotRolagem());
        System.out.println("Rolagem salva no Log");
    }

    public String getLog(){
        StringBuilder sb = new StringBuilder();
//        ListIterator<ArrayList<Integer>> listIterator = todasRolagens.listIterator();
//        while(listIterator.hasNext()){
//            s += "Rolagem " + (listIterator.nextIndex() + 1) + ": " + "soma("+ somaRolagens.get(listIterator.nextIndex()) +") - "  + listIterator.next() + "\n";
//        }

        int index = 0;
        for (Rolagem rolagem : todasRolagens) {
             sb.append("Rolagem " + (index+1) + ": - "  + rolagem.getTotEValores() + "\n");
            index++;
        }

        return sb.toString();
    }

    public String getStrUltimaRolagem(){
        StringBuilder sb = new StringBuilder();
        sb.append("Rolagem: " + todasRolagens.get(todasRolagens.size()-1).getTotEValores());
        return sb.toString();
    }





//    public void imprimirTodasRolagens(){
//
//        // Iterando sobre o ArrayList principal
//        ListIterator<ArrayList<Integer>> iterator = log.getTodasRolagens().listIterator();
//        while (iterator.hasNext()) {
//            ArrayList<Integer> rolagem = iterator.next();
//            System.out.println("Rolagem " + iterator.nextIndex() + " - " + rolagem.toString());
//            /*
//            // Iterando sobre os ArrayLists internos
//            Iterator<Integer> iteratorInterno = rolagem.iterator();
//            while (iteratorInterno.hasNext()) {
//                Integer valor = iteratorInterno.next();
//                System.out.println("Valor: " + valor);
//            }*/
//        }
//    }
}
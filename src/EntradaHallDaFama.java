public class EntradaHallDaFama implements Comparable<EntradaHallDaFama> {

    public String nome;
    public int pontuacao;

    EntradaHallDaFama(){
        nome = "";
        pontuacao = 0;
    }


    public int compareTo(EntradaHallDaFama entrada) {
        return 0;
    }

//    public int compare(EntradaHallDaFama c1, EntradaHallDaFama c2){
//        EntradaHallDaFama a1 = c1.getColor();
//        EntradaHallDaFama a2 = c2.getColor();
//        return sortOrder.indexOf(a1) - sortOrder.indexOf(a2);
//    }

}

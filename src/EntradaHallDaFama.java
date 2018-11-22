import java.io.Serializable;

public class EntradaHallDaFama implements Comparable<EntradaHallDaFama>, Serializable {

    public String nome;
    public int pontuacao;

    EntradaHallDaFama(){
        nome = "";
        pontuacao = 0;
    }
    EntradaHallDaFama(String nome, int pontuacao){
        this.nome = nome;
        this.pontuacao = pontuacao;
    }

    public int compareTo(EntradaHallDaFama outraEntrada) {

//        os returns estao ao contrario para mostrar na ordem inversa
        
        if (this.pontuacao < outraEntrada.pontuacao){
            return 1;
        }else if(this.pontuacao > outraEntrada.pontuacao){
            return -1;
        }else{
            return 0;
        }
    }


}

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

//Map<String, String> map = new HashMap<String, String>();
//map.put("dog", "type of animal");
//System.out.println(map.get("dog"));

public class HallDaFama implements Serializable {
    private ArrayList<EntradaHallDaFama> entradas;

    HallDaFama(){
//        carrega do arquivo
    }


    public int getMax(){
        int maximo = 0;
        for (EntradaHallDaFama entrada : entradas){
            if (entrada.pontuacao > maximo){
                maximo = entrada.pontuacao;
            }
        }
        return maximo;
    }

    public void novaEntrada(EntradaHallDaFama entrada){
        entradas.add(entrada);
        if (entradas.size() > 10){
            entradas.remove(entradas.size() - 1);
        }
    }

    public String getTexto(){
        String texto = "";
        for (EntradaHallDaFama entrada : entradas) {
            texto += entrada.nome + "    " + entrada.pontuacao + "<br>";
        }
        return texto;
    }

}

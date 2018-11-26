import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

//Map<String, String> map = new HashMap<String, String>();
//map.put("dog", "type of animal");
//System.out.println(map.get("dog"));

public class HallDaFama implements Serializable {
    private ArrayList<EntradaHallDaFama> entradas;

    HallDaFama(){

        FileInputStream arquivo = null;
        try {
            arquivo = new FileInputStream("halldafama.conf");

            HallDaFama halldafama = null;
            ObjectInputStream restaurador = new ObjectInputStream(arquivo);
            halldafama = (HallDaFama)restaurador.readObject();
            this.entradas = halldafama.entradas;
            restaurador.close();
            arquivo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (this.entradas == null){
            this.entradas = new ArrayList<EntradaHallDaFama>();
        }

        try {
            salvar();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(entradas);


    }

    public void salvar()throws IOException {
        FileOutputStream arquivo = new FileOutputStream("halldafama.conf");
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(this);
        gravador.close();
        arquivo.close();
    }

    //verifica se com a pontuacao
    public boolean deveEntrarParaOHallDaFama(int pontuacao){
        //pontuacao deve ser maior que zero
//        verdadeiro se nao tiver 10 entradas cadastradas
        //ou se a pontuacao fornecida for maior que a decima
        //
        return (pontuacao > 0 && (entradas.size() < 10 || pontuacao > getMenorPontuacao()));
    }
    public int getMaiorPontuacao(){
        if (entradas.size() > 0){
            Collections.sort(entradas);
            return entradas.get(0).pontuacao;
        }else{
            return 0;
        }
    }

    public int getMenorPontuacao(){
        if (entradas.size() > 0){
            Collections.sort(entradas);
            return entradas.get(entradas.size() - 1).pontuacao;
        }else{
            return 0;
        }
    }

    public void novaEntrada(EntradaHallDaFama entrada){
        entradas.add(entrada);
        Collections.sort(entradas);
        if (entradas.size() > 10){
            entradas.remove(entradas.size() - 1);
        }

        try {
            salvar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTexto(){
        String texto = "<html><table>";
        Collections.sort(entradas);
        for (EntradaHallDaFama entrada : entradas) {
            texto += "<tr><td>" + entrada.nome + "</td><td>" + entrada.pontuacao + "</td></tr>";
        }
        texto += "</html>";

        return texto;
    }

}

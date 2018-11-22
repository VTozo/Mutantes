import java.io.*;

public class Configuracao implements Serializable{

//    . Largura inicial do território (No exemplo, a largura é 400.)
//2. Altura inicial do território (No exemplo, a altura é 300.)
//3. Máximo de gerações (No exemplo, esse número é 3.)
//4. Tempo do passo (Usado como parâmetro em Thread.sleep no método jogar da
//classe Territorio; quanto menor, mais rápido fica o jogo. No exemplo, esse tempo
//é de 20 milissegundos.)
//5. Tempo máximo de ociosidade (Um nova geração de seres é criada quando o
//tempo sem ocorrer qualquer colisão atinge esse máximo; se o jogo já estiver na
//última geração, é encerrado. No exemplo, esse tempo é de 10 segundos.)
//6. Mínimo de seres (Quando o número de seres no território fica abaixo desse mínimo,
// uma nova geração é criada;
// se o jogo já estiver na última geração, é
//encerrado. No exemplo, esse mínimo é 3.)
//7. Fator máximo de aumento linear (Usado para determinar o tamanho máximo de
//um ser antes que entre em processo de fissão. No exemplo, esse fator é 3,
// isto é, um ser pode ficar até 3 vezes maior,
// isto é, pode chegar, no máximo, ao tamanho de 3 seres, considerando o raio, e não a área.)


    public String personagem;
    public int largura;
    public int altura;
    public int maxGeracoes;
    public double tempoPasso;
    public double tempoMaximoOciosidade;
    public int minimoSeres;
    public int maximoAumentoLinear;
    public int ociosidade;

    public Configuracao(){

        personagem = "menino";
        largura = 400;
        altura = 300;
        maxGeracoes = 3;
        tempoPasso = 1000/30;
        tempoMaximoOciosidade = 1;
        minimoSeres = 1;
        maximoAumentoLinear = 3;
        ociosidade = 100;

    }

    public void alterar(String personagem, int largura, int altura, int maxGeracoes, double tempoPasso,
                        double tempoMaximoOciosidade, int minimoSeres, int maximoAumentoLinear, int ociosidade){
        this.personagem = personagem;
        this.largura = largura;
        this.altura = altura;
        this.maxGeracoes = maxGeracoes;
        this.tempoPasso = tempoPasso;
        this.tempoMaximoOciosidade = tempoMaximoOciosidade;
        this.minimoSeres = minimoSeres;
        this.maximoAumentoLinear = maximoAumentoLinear;
        this.ociosidade = ociosidade;
    }

    public void salvar()throws IOException {
        FileOutputStream arquivo = new FileOutputStream("config.conf");
        ObjectOutputStream gravador = new ObjectOutputStream(arquivo);
        gravador.writeObject(this);
        gravador.close();
        arquivo.close();
    }

    public static Configuracao abrir() throws IOException, ClassNotFoundException{
        Configuracao configuracao = null;
        FileInputStream arquivo = new FileInputStream("config.conf");
        ObjectInputStream restaurador = new ObjectInputStream(arquivo);
        configuracao = (Configuracao) restaurador.readObject();
        restaurador.close();
        arquivo.close();
        return configuracao;
    }
}

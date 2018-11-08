public class Configuracao {

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


    public String personagem = "menino";
    public int largura = 400;
    public int altura = 300;
    public int maxGeracoes = 3;
    public double tempoPasso = 1000/60;
    public double tempoMaximoOciosidade = 1;
    public int minimoSeres = 1;
    public int maximoAumentoLinear = 3;
    public int ociosidade = 100;

}

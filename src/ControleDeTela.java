import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;


public class ControleDeTela extends JPanel implements TelaInicialInterface, TerritorioInterface, TelaHallDaFamaInterface{
    int largura;
    int altura;

    public JFrame frame;

    TelaInicial telaInicial;
    TelaHallDaFama telaHallDaFama;
    Territorio territorio;
    TelaConfiguracoes telaConfiguracoes;

    ControleDeTela() {

        Configuracao configuracao = Configuracao.getInstance();


        largura = configuracao.janelaPrincipalLargura;
        altura = configuracao.janelaPrincipalAltura;

        GridLayout layout = new GridLayout(1,0);
        this.setLayout(layout);



        frame = new JFrame("FrameDoJogo");
        frame.getContentPane().add(this);

        frame.setSize(configuracao.janelaPrincipalLargura, configuracao.janelaPrincipalAltura);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);

        telaInicial = new TelaInicial(this);
        this.add(telaInicial);

        frame.setVisible(true);

        //        O Controle de tela é quem vai receber os Eventos de Tecla

        KeyListener listener = LeitorSetas.getInstance();
        System.out.println("listener: " + listener);
        addKeyListener(listener);


    }

//Interface da Tela Inicial
    public void btnJogarPressionado(){

        setFocusable(true);

        telaInicial.setVisible(false);

        this.remove(telaInicial);

        territorio = new Territorio(this);

        this.add(territorio);
        territorio.setVisible(true);
        this.configurarLeitorDeSetas();

        territorio.jogar();
    }

    public void btnHallDaFamaPressionado(){
        System.out.println("btnHallDaFamaPressionado");
        System.out.println(telaHallDaFama);
        this.remove(telaInicial);
        this.revalidate();
        this.repaint();

        if (telaHallDaFama == null){
            telaHallDaFama = new TelaHallDaFama(this);
        }

        this.add(telaHallDaFama);
        telaInicial.setVisible(false);

        telaHallDaFama.setVisible(true);
    }

    public void btnConfiguracoesPressionado(){
        System.out.println("btnConfiguracoesPressionado");

        this.remove(telaInicial);
        this.revalidate();
        this.repaint();

        if (telaConfiguracoes == null){
            telaConfiguracoes = new TelaConfiguracoes(this);
        }

        this.add(telaConfiguracoes);
        telaInicial.setVisible(false);

        telaConfiguracoes.setVisible(true);

    }

//    Interface do Territorio
    public void gameOver(){
        frame.setSize(largura,altura);
        this.remove(territorio);
        territorio.setVisible(false);
        territorio.racional = null;
        territorio = null;

        this.add(telaInicial);
        this.revalidate();
        this.repaint();

        telaInicial.setVisible(true);
    }



//  Interface Hall Da Fama
    public void btnVoltarPressionado(){

        System.out.println("btnVoltarPressionado");
        if (telaHallDaFama != null && telaHallDaFama.isVisible()){
            this.remove(telaHallDaFama);
            telaHallDaFama.setVisible(false);
            telaHallDaFama = null;
        }else if (telaConfiguracoes != null && telaConfiguracoes.isVisible()){
            this.remove(telaConfiguracoes);
            telaConfiguracoes.setVisible(false);
            telaConfiguracoes = null;
        }
        this.add(telaInicial);
        this.revalidate();
        this.repaint();

        telaInicial.setVisible(true);
    }

//    CONTROLE DE SETAS
    private void configurarLeitorDeSetas(){
//        como existem varias telas que podem estar em primeiro plano,
// deve-se configurar o controle de setas para dar o devido tratamento
//        de acordo com a tela que estiver em primeiro plano

        System.out.println("------------");
        System.out.println("configurar setas");
        System.out.println("Leitor: " + LeitorSetas.getInstance());
        System.out.println("territorio: " + territorio);
        System.out.println("territorio.racional: " + territorio.racional);
        System.out.println("------------");

        if (territorio != null){
//            territorio esta em primeiro plano
            LeitorSetas.getInstance().setRacional(territorio.racional);
            this.setFocusable(true);
        }else{

        }
    }

}

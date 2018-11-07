import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;


public class ControleDeTela extends JPanel implements TelaInicialInterface, TerritorioInterface, HallDaFamaInterface{
    int largura = 400;
    int altura = 400;

    public JFrame frame;

    TelaInicial telaInicial;
    HallDaFama hallDaFama;
    Territorio territorio;
    TelaConfiguracoes telaConfiguracoes;

    ControleDeTela() {

        GridLayout layout = new GridLayout(1,0);
        this.setLayout(layout);

        frame = new JFrame("FrameDoJogo");
        frame.getContentPane().add(this);
        frame.setSize(largura, altura);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);

        telaInicial = new TelaInicial(this);
        this.add(telaInicial);


        frame.setVisible(true);
    }

//Interface da Tela Inicial
    public void btnJogarPressionado(){
        System.out.println("btnJogarPressionado");
        telaInicial.setVisible(false);

        this.remove(telaInicial);

        territorio = new Territorio(this);
        this.add(territorio);

//        O Controle de tela é quem vai receber os Eventos de Tecla
        KeyListener listener = new LeitorSetas(territorio.racional);
        addKeyListener(listener);
        setFocusable(true);

        territorio.jogar();
    }

    public void btnHallDaFamaPressionado(){
        System.out.println("btnHallDaFamaPressionado");
        System.out.println(hallDaFama);
        this.remove(telaInicial);
        this.revalidate();
        this.repaint();

        if (hallDaFama == null){
            hallDaFama = new HallDaFama(this);
        }

        this.add(hallDaFama);
        telaInicial.setVisible(false);

        hallDaFama.setVisible(true);
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

        this.remove(territorio);
        territorio = null;

        this.add(telaInicial);
        this.revalidate();
        this.repaint();

        telaInicial.setVisible(true);
    }



//  Interface Hall Da Fama
    public void btnVoltarPressionado(){

        System.out.println("btnVoltarPressionado");
        if (hallDaFama != null && hallDaFama.isVisible()){
            this.remove(hallDaFama);
            hallDaFama.setVisible(false);
            hallDaFama = null;
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

}

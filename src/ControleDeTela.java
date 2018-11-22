import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;


public class ControleDeTela extends JPanel implements TelaInicialInterface, TerritorioInterface, TelaHallDaFamaInterface{
    int largura = 400;
    int altura = 400;

    public JFrame frame;

    TelaInicial telaInicial;
    TelaHallDaFama telaHallDaFama;
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

        KeyListener listener = LeitorSetas.getInstance();
        System.out.println("listener: " + listener);
        ((LeitorSetas) listener).setRacional(territorio.racional);

        addKeyListener(listener);
        setFocusable(true);


        this.add(territorio);
        territorio.setVisible(true);
        System.out.println("racional: " + territorio.racional);
//        O Controle de tela é quem vai receber os Eventos de Tecla



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

}

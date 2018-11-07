import javax.swing.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.nio.Buffer;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class ControleDeTela extends JPanel implements TelaInicialInterface, TerritorioInterface, HallDaFamaInterface{
    int largura = 400;
    int altura = 400;
    JFrame frame;

    TelaInicial telaInicial;
    HallDaFama hallDaFama;
    Territorio territorio;


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
//        System.out.println("antes: " + this.getLayout());
        this.remove(telaInicial);
//        this.revalidate();
//        this.repaint();
//
        territorio = new Territorio(this);
        this.add(territorio);
        System.out.println("depois: " + this.getLayout());
        territorio.jogar();
//
//
//
//        telaInicial.setVisible(false);
//        territorio.setVisible(true);
//
//        this.revalidate();
//        this.repaint();
//
//        territorio.jogar();
//        this.revalidate();
//        this.repaint();
//
//        System.out.println(territorio);

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

//    Interface do Territorio
    public void gameOver(){
        System.out.println("Game Over");

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
        this.remove(hallDaFama);
        this.add(telaInicial);
        this.revalidate();
        this.repaint();

        telaInicial.setVisible(true);
    }
}

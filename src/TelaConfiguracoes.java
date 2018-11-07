import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.BoxLayout;


public class TelaConfiguracoes extends JPanel {
    int largura = 400;
    int altura = 400;
    ControleDeTela controleDeTela;

    TelaConfiguracoes(ControleDeTela controleDeTela) {

        this.controleDeTela = controleDeTela;

        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

//        this.setLayout(layout);

        this.setBackground(Color.black);

        JLabel lblEscolha = new JLabel();
        lblEscolha.setText("Escolha seu personagem");
        lblEscolha.setForeground(Color.white);
        this.add(lblEscolha);


        try {
            BufferedImage image = ImageIO.read(new File("src/imagens/mutantes_logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(image));

            this.add(picLabel);


            Border emptyBorder = BorderFactory.createEmptyBorder();
            Border selectBorder = BorderFactory.createLineBorder(Color.RED);

//            botao menina
            int NEW_WIDTH = 60;
            int NEW_HEIGHT = 60;

            BufferedImage bufferImgMenina = ImageIO.read(new File("src/imagens/personagem_menina.png"));
            Image imgMenina = bufferImgMenina.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;

            JButton btnMenina = new JButton();
            btnMenina.setBorder(selectBorder);
            btnMenina.setIcon(new ImageIcon(imgMenina));
            btnMenina.setSize(30,30);
            this.add(btnMenina);

//            botao menino
            BufferedImage bufferImgMenino = ImageIO.read(new File("src/imagens/personagem_menino.png"));
            Image imgMenino = bufferImgMenino.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;
            JButton btnMenino = new JButton();
            btnMenino.setBorder(emptyBorder);
            btnMenino.setIcon(new ImageIcon(imgMenino));
            btnMenino.setSize(30,30);
            this.add(btnMenino);




//          botao voltar
            BufferedImage imgBtnVoltar = ImageIO.read(new File("src/imagens/botao_voltar.png"));
            JButton btnVoltar = new JButton();
            btnVoltar.setBorder(emptyBorder);
            btnVoltar.setIcon(new ImageIcon(imgBtnVoltar));

            btnVoltar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    action_btnVoltar();
                }
            });

            this.add(btnVoltar);


        } catch (IOException ex) {
            // handle exception...
        }



    }

    private void action_btnVoltar(){
        controleDeTela.btnVoltarPressionado();
    }

}

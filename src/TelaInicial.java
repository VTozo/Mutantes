import sun.tools.jstat.Alignment;

import javax.swing.*;
import java.awt.Graphics;
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


public class TelaInicial extends JPanel {

    int largura = 400;
    int altura = 400;

    TelaInicial() {

        JFrame frame = new JFrame();
        frame.add(this);
        frame.setSize(largura, altura);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);
        this.setBackground(Color.black);

        try {
            BufferedImage image = ImageIO.read(new File("src/imagens/mutantes_logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(image));

            this.add(picLabel);

            BufferedImage imgBtnJogar = ImageIO.read(new File("src/imagens/botao_jogar.png"));

            Border emptyBorder = BorderFactory.createEmptyBorder();

            JButton btnJogar = new JButton();
            btnJogar.setBorder(emptyBorder);
            btnJogar.setIcon(new ImageIcon(imgBtnJogar));
            this.add(btnJogar);


            BufferedImage imgBtnHalldaFama = ImageIO.read(new File("src/imagens/botao_hall_da_fama.png"));

            JButton btnHallOfFame = new JButton();
            btnHallOfFame.setBorder(emptyBorder);
            btnHallOfFame.setIcon(new ImageIcon(imgBtnHalldaFama));
            this.add(btnHallOfFame);

        } catch (IOException ex) {
            // handle exception...
        }

        frame.setVisible(true);






    }




}

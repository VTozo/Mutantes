import sun.tools.jstat.Alignment;

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


public class TelaInicial extends JPanel implements ActionListener {

    int largura = 400;
    int altura = 400;
    private JFrame frame;

    TelaInicial() {

        frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setSize(largura, altura);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);

        GridLayout layout = new GridLayout(0,1);
        this.setLayout(layout);

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

            btnJogar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    action_btnJogar();
                }
            });

            this.add(btnJogar);


            BufferedImage imgBtnHalldaFama = ImageIO.read(new File("src/imagens/botao_hall_da_fama.png"));

            JButton btnHallOfFame = new JButton();
            btnHallOfFame.setBorder(emptyBorder);
            btnHallOfFame.setIcon(new ImageIcon(imgBtnHalldaFama));
            btnHallOfFame.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    action_btnHallDaFama();
                }
            });

            this.add(btnHallOfFame);

        } catch (IOException ex) {
            // handle exception...
        }

        JLabel labelCreditos = new JLabel();
        labelCreditos.setText("<html>Créditos<br>" +
                "Prof. Dr. Alcides Calsavara<br>"+
                "Guilherme Miranda<br>"+
                "Ricardo Varjão<br>"+
                "Vinícius Tozo</html>"
        );

        labelCreditos.setForeground(Color.WHITE);
        this.add(labelCreditos);

        frame.setVisible(true);

    }



    private void action_btnJogar(){
        frame.setVisible(false);
        Territorio territorio = new Territorio("Territorio");
        territorio.jogar();
    }

    private void action_btnHallDaFama(){
        System.out.println("Abre a tela do Hall da Fama");
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}

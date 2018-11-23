import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class TelaHallDaFama extends JPanel {


    ControleDeTela controleDeTela;

    JLabel labelScores;

    TelaHallDaFama(ControleDeTela controleDeTela) {
        this.controleDeTela = controleDeTela;

        GridLayout layout = new GridLayout(0,1);
        this.setLayout(layout);

        this.setBackground(Color.black);



        try {
            BufferedImage image = ImageIO.read(new File("src/imagens/mutantes_logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(image));
            this.add(picLabel);

            labelScores = new JLabel();
            labelScores.setForeground(Color.WHITE);
            labelScores.setHorizontalAlignment(SwingConstants.CENTER);
            labelScores.setText("COLOCAR O TEXTO DE SCORES AQUI");
            this.add(labelScores);

            BufferedImage imgBtnVoltar = ImageIO.read(new File("src/imagens/botao_voltar.png"));

            Border emptyBorder = BorderFactory.createEmptyBorder();

            JButton btnVoltar = new JButton();
            btnVoltar.setBorder(emptyBorder);
            btnVoltar.setIcon(new ImageIcon(imgBtnVoltar));

            btnVoltar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    action_btnVoltar();
                }
            });

            this.add(btnVoltar);

            HallDaFama hallDaFama = new HallDaFama();
            String text = hallDaFama.getTexto();
            System.out.println(text);
            labelScores.setText(text);

        } catch (IOException ex) {
            // handle exception...
        }
    }

    private void action_btnVoltar(){
        controleDeTela.btnVoltarPressionado();
    }

}

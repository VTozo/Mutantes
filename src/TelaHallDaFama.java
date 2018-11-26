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

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;


        this.setBackground(Color.black);



        try {
            BufferedImage image = ImageIO.read(new File("src/imagens/mutantes_logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(image));


            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridheight = 1;

            this.add(picLabel, constraints);

            labelScores = new JLabel();
            labelScores.setForeground(Color.WHITE);
            labelScores.setHorizontalAlignment(SwingConstants.CENTER);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            constraints.gridheight = 6;

            this.add(labelScores, constraints);

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

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 7;
            constraints.gridheight = 1;

            this.add(btnVoltar, constraints);

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

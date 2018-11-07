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


public class TelaInicial extends JPanel implements ActionListener {

    int largura = 400;
    int altura = 400;
    ControleDeTela controleDeTela;

    TelaInicial(ControleDeTela controleDeTela) {
        this.controleDeTela = controleDeTela;

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

    }



    private void action_btnJogar(){
        controleDeTela.btnJogarPressionado();
    }

    private void action_btnHallDaFama(){
        controleDeTela.btnHallDaFamaPressionado();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}

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

    JButton btnMenina;
    JButton btnMenino;

    Configuracao configuracao;


    TelaConfiguracoes(ControleDeTela controleDeTela) {

        this.controleDeTela = controleDeTela;

        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        configuracao = new Configuracao();

        try {
            configuracao = configuracao.abrir();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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

//            botao menina
            int NEW_WIDTH = 60;
            int NEW_HEIGHT = 60;

            Border emptyBorder = BorderFactory.createEmptyBorder();
            Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);

            BufferedImage bufferImgMenina = ImageIO.read(new File("src/imagens/personagem_menina.png"));
            Image imgMenina = bufferImgMenina.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;

            btnMenina = new JButton();
            btnMenina.setIcon(new ImageIcon(imgMenina));
            btnMenina.setSize(30,30);
            btnMenina.setBorder(blackBorder);

            btnMenina.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selecionarPersonagem("menina");
                }
            });
            this.add(btnMenina);

//            botao menino
            BufferedImage bufferImgMenino = ImageIO.read(new File("src/imagens/personagem_menino.png"));
            Image imgMenino = bufferImgMenino.getScaledInstance( NEW_WIDTH, NEW_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;
            btnMenino = new JButton();
            btnMenino.setIcon(new ImageIcon(imgMenino));
            btnMenino.setSize(30,30);
            btnMenino.setBorder(blackBorder);

            btnMenino.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selecionarPersonagem("menino");
                }
            });
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

        String personagem = configuracao.personagem;
        selecionarPersonagem(personagem);

    }

    private void action_btnVoltar(){
        try {
            configuracao.salvar();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controleDeTela.btnVoltarPressionado();
    }

    private void selecionarPersonagem(String personagem){
        Border selectedBorder = BorderFactory.createLineBorder(Color.RED);
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);

        if (personagem.equals("menina")){
            btnMenina.setBorder(selectedBorder);
            btnMenino.setBorder(blackBorder);
        }else{
            btnMenino.setBorder(selectedBorder);
            btnMenina.setBorder(blackBorder);
        }

        configuracao.personagem = personagem;

    }

}

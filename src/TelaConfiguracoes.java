import sun.jvm.hotspot.utilities.soql.JSList;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class TelaConfiguracoes extends JPanel {

    ControleDeTela controleDeTela;

    private JButton btnMenina;
    private JButton btnMenino;

    private Configuracao configuracao;

    private JSlider sliderLargura;
    private JSlider sliderAltura;

    private JLabel labelAltura;
    private JLabel labelLargura;


    private JLabel labelMaxGeracoes;
    private JLabel labelTempoPasso;
    private JLabel labelTempoOcisidade;
    private JLabel labelMinimoSeres;
    private JLabel labelAumentoLinear;

    private JSlider sliderMaxGeracoes;
    private JSlider sliderTempoPasso;
    private JSlider sliderTempoOcisidade;
    private JSlider sliderMinimoSeres;
    private JSlider sliderAumentoLinear;



    private ChangeListener listener;

    TelaConfiguracoes(ControleDeTela controleDeTela) {

        this.controleDeTela = controleDeTela;

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.HORIZONTAL;

        configuracao = new Configuracao();

        Configuracao configuracao = new Configuracao();


//        this.setLayout(layout);

//        GridLayout layout = new GridLayout(0,2);
//        this.setLayout(layout);

        this.setBackground(Color.darkGray);




        try {
            BufferedImage image = ImageIO.read(new File("src/imagens/mutantes_logo.png"));
            JLabel picLabel = new JLabel(new ImageIcon(image));


            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.gridwidth = 2;
            this.add(picLabel,constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 2;
            JLabel lblEscolha = this.configurarLabel();
            lblEscolha.setText("Escolha seu personagem");
            constraints.gridy = 1;
            this.add(lblEscolha, constraints);

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

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            constraints.gridwidth = 1;

            this.add(btnMenina,constraints);

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

            constraints.gridx = 1;
            constraints.weightx = 0.5;
            this.add(btnMenino, constraints);
            // common listener for all sliders


            listener = new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    // update text field when the slider value changes
                    JSlider source = (JSlider) event.getSource();

                    if (source == sliderAltura){
                        int valorCorreto = Math.round(source.getValue()/10)*10;
                        sliderAltura.setValue(valorCorreto);

                    }else if (source == sliderLargura){
                        int valorCorreto = Math.round(source.getValue()/10)*10;
                        sliderLargura.setValue(valorCorreto);

                    }else if (source == sliderAumentoLinear){

                    }else if (source == sliderMaxGeracoes){

                    }else if (source == sliderMinimoSeres){

                    }else if (source == sliderTempoOcisidade){

                    }else if (source == sliderTempoPasso){

                    }

                    updateLabels();
                    updateConfiguracao();

                }

            };

            sliderLargura = this.configurarSlider(300, 500,configuracao.largura);
            sliderAltura = this.configurarSlider(300, 500,configuracao.altura);
            sliderAumentoLinear = this.configurarSlider(1, 5,configuracao.maximoAumentoLinear);
            sliderMaxGeracoes = this.configurarSlider(1, 10,configuracao.maxGeracoes);
            sliderMinimoSeres = this.configurarSlider(1, 10,configuracao.minimoSeres);
            sliderTempoOcisidade = this.configurarSlider(1, 100,(int)configuracao.tempoMaximoOciosidade);
            sliderTempoPasso = this.configurarSlider(30, 120,(int)configuracao.tempoPasso);

            labelAltura = this.configurarLabel();
            labelLargura = this.configurarLabel();
            labelAumentoLinear = this.configurarLabel();
            labelMaxGeracoes = this.configurarLabel();
            labelMinimoSeres = this.configurarLabel();
            labelTempoOcisidade = this.configurarLabel();
            labelTempoPasso = this.configurarLabel();


//----
            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            constraints.weightx = 0.2;
            this.add(labelAltura, constraints);

            constraints.gridx = 1;
            constraints.weightx = 1;
            this.add(sliderAltura, constraints);
//----

            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            this.add(labelLargura, constraints);
            constraints.gridx = 1;
            this.add(sliderLargura, constraints);
//----

            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            this.add(labelAumentoLinear, constraints);
            constraints.gridx = 1;
            this.add(sliderAumentoLinear, constraints);
//----

            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            this.add(labelMaxGeracoes, constraints);
            constraints.gridx = 1;
            this.add(sliderMaxGeracoes, constraints);
//----

            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            this.add(labelMinimoSeres, constraints);
            constraints.gridx = 1;
            this.add(sliderMinimoSeres, constraints);
//----

            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            this.add(labelTempoOcisidade, constraints);
            constraints.gridx = 1;
            this.add(sliderTempoOcisidade, constraints);
//----

            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            this.add(labelTempoPasso, constraints);
            constraints.gridx = 1;
            this.add(sliderTempoPasso, constraints);
//----


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

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = constraints.gridy + 1;
            constraints.gridwidth = 2;
            this.add(btnVoltar, constraints);


        } catch (IOException ex) {
            // handle exception...
        }

        String personagem = configuracao.personagem;
        selecionarPersonagem(personagem);

        updateLabels();

    }

    private void action_btnVoltar(){
        configuracao.largura = sliderLargura.getValue();
        configuracao.altura = sliderAltura.getValue();

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


    private JSlider configurarSlider(int min, int max, int value){
        System.out.println("min: " + min + "  max: " + max + "  value: " + value);
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min,max,value);

        slider.setMajorTickSpacing((max - min)/5);
        slider.setMinorTickSpacing((max - min)/10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(Color.WHITE);
        slider.setBackground(Color.WHITE);
        slider.setForeground(Color.red);
        System.out.println(listener);
        slider.addChangeListener(listener);

        return slider;

    }

    private JLabel configurarLabel(){
        JLabel label = new JLabel();
        label.setForeground(Color.white);
        return label;
    }

    private void updateLabels(){
        labelAltura.setText("altura: " + sliderAltura.getValue());
        labelLargura.setText("largura: " + sliderLargura.getValue());
        labelTempoPasso.setText("FPS: " + sliderTempoPasso.getValue());
        labelTempoOcisidade.setText("tempo ociosidade: " + sliderTempoOcisidade.getValue());
        labelMinimoSeres.setText("mínimo de seres: " + sliderMinimoSeres.getValue());
        labelMaxGeracoes.setText("max gerações: " + sliderMaxGeracoes.getValue());
        labelAumentoLinear.setText("aumento linear: " + sliderAumentoLinear.getValue());
    }

    private void updateConfiguracao(){
        configuracao.largura = sliderLargura.getValue();
        configuracao.altura = sliderAltura.getValue();
        configuracao.tempoPasso = sliderTempoPasso.getValue();
        configuracao.tempoMaximoOciosidade = sliderTempoOcisidade.getValue();
        configuracao.minimoSeres = sliderMinimoSeres.getValue();
        configuracao.maxGeracoes = sliderMaxGeracoes.getValue();
        configuracao.maximoAumentoLinear = sliderAumentoLinear.getValue();

    }


}

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


class Territorio extends JPanel {
    private int contador = 0;
    private ArrayList<Ser> seres = new ArrayList<Ser>();
    public Racional racional;

    int largura;
    int altura;
    private boolean jogando = true;
    private boolean perdeu = false;
    private int geracao = 0;
    private int geracao_maxima = 6;
    private int ociosidade = 100;
    private int nivel_de_fome = 0;
    private int pontuacao = 0;
    private int minimoSeres = 0;
    Configuracao configuracao = Configuracao.getInstance();

    private ControleDeTela controleDeTela;

    Territorio(ControleDeTela controleDeTela) {

        this.controleDeTela = controleDeTela;

        Configuracao configuracao = Configuracao.getInstance();

        altura = configuracao.altura;
        largura = configuracao.largura;

        minimoSeres = configuracao.minimoSeres;


        this.setSize(largura,altura);
        this.controleDeTela.frame.setSize(largura, altura);

        this.setVisible(true);
        try{
            racional = new Racional(this);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void paint(Graphics g) {

    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    //Solucionando problema de ConcurrentModificationException - Isabela Assis
    for (int i = 0; i < seres.size(); i++) {
        Ser ser = seres.get(i);
        if (ser != null) ser.paint(g);
    }


    if (racional != null) racional.paint(g);

    g2d.setColor(Color.DARK_GRAY);
    g2d.setFont(new Font("Consolas", Font.BOLD, 16));
    g2d.drawString("Geração: " + String.valueOf(geracao) + "/" + String.valueOf(geracao_maxima), 10, 20);
    g2d.drawString("Ociosidade: " + String.valueOf(ociosidade), 10, 40);
    g2d.drawString("Pontuacao: " + String.valueOf(pontuacao), 10, 60);


    //g2d.drawString("Fome: " + String.valueOf(nivel_de_fome), 10, 80);

    }


//  ESTA COM PROBLEMA DE ATUALIZACAO DOS OBJETOS DA TELA
//  ISSO DEVE SER FEITO NA MAIN THREAD, MAS NAO SEI COMO! (AINDA)
    
    void jogar() {
        System.out.println("inicio do jogo");

        Thread loop = new Thread() {
            public void run() {
                gameLoop();
            }
        };

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        loop.start();

    }

    private void gameLoop(){


        while (jogando) {

            if (ociosidade <= 0 || seres.size() < minimoSeres) {
                if (geracao < geracao_maxima) {
                    gerar_seres(configuracao.minimoSeres * 2);
                    geracao++;
                    Sounds.playSound("TrocaGeracao.wav");
                    ociosidade = configuracao.tempoMaximoOciosidade;
                } else if (ociosidade <= 0 || seres.size() == 1) {
                    if (seres.get(0).get_em_fissao() == 0) {
                        jogando = false;
                    }
                } else if (seres.size() == 0) {
                    jogando = false;
                }

            }
            if (contador % 10 == 0) {
                ociosidade--;
                nivel_de_fome++;
                if (nivel_de_fome > 100) pontuacao--;
                if (pontuacao <= -20) jogando = false;
            }

            racional.mover();
            detectar_colisoes();
            detectar_fissoes();

            for (Ser ser : seres){
                ser.mover();
                int diametro_maximo = 40;
                if (ser.getDiametro() > diametro_maximo && ser.get_em_fissao() == 0) {
                    ser.iniciar_fissao();
                }
            }

            repaint();


//            deve pegar do fps das configuracoes

            try {
                Thread.sleep(1000 / (long)configuracao.tempoPasso);
            } catch (Exception e) {
                e.printStackTrace();
            }
            contador++;

        }

        if (perdeu) game_over(0);
        else if (pontuacao <= -20) game_over(1);
        else game_over(2);

    }

    private void detectar_fissoes() {
        ArrayList<Ser> seres_to_add = new ArrayList<Ser>();

        for (Ser ser: seres
             ) {
            if(ser.get_em_fissao() == ser.getDiametro()){

                ser.setDiametro(ser.getDiametro()/2);

                int new_x = ser.getPosicao()[0]+ser.getDiametro();
                int new_y = ser.getPosicao()[1]+ser.getDiametro();
                if(ser.getDirecao() == 1){
                    new_x = ser.getPosicao()[0]-ser.getDiametro();
                    new_y = ser.getPosicao()[1]+ser.getDiametro();
                }
                else if(ser.getDirecao() == 2){
                    new_x = ser.getPosicao()[0]-ser.getDiametro();
                    new_y = ser.getPosicao()[1]-ser.getDiametro();
                }
                else if(ser.getDirecao() == 3){
                    new_x = ser.getPosicao()[0]+ser.getDiametro();
                    new_y = ser.getPosicao()[1]-ser.getDiametro();
                }

                Color new_cor = Color.GREEN;
                if (ser.getCor() == Color.GREEN){
                    ser.setCor(Color.BLUE);
                    new_cor = Color.YELLOW;
                }

                seres_to_add.add(new Ser(this, new_x, new_y, ser.getDiametro(), new_cor, (ser.getDirecao()+2)%4));
                ser.set_em_fissao(0);

            }
        }

        seres.addAll(seres_to_add);
    }

    private void detectar_colisoes() {

        for (int i=0; i<seres.size(); i++) {

            Ser s1 = seres.get(i);
            if(s1.getRetangulo().intersects(racional.getRetangulo())){
                if (seres.get(i).getCor() == Color.GREEN){
                    pontuacao += seres.get(i).getDiametro();
                    nivel_de_fome = 0;
                    seres.remove(i);
                    Sounds.playSound("BolinhaComida.wav");
                    break;
                }
                else {
                    jogando = false;
                    perdeu = true;
                }
            }

            for (int c=0; c<seres.size(); c++) {
                if(i!=c){
                    Ser s2 = seres.get(c);
                    if(s1.getRetangulo().intersects(s2.getRetangulo())
                            && s1.getPosicao() != s2.getPosicao()){
                        fusao(i,c);
                        Sounds.playSound("JuncaoBolinhas.wav");
                        ociosidade = configuracao.tempoMaximoOciosidade;
                        break;
                    }
                }

            }
        }
    }

    private void fusao(int indice_s1, int indice_s2) {
        Ser s1 = seres.get(indice_s1);
        Ser s2 = seres.get(indice_s2);

        if(s1.getDiametro() > s2.getDiametro()){
            s1.setDiametro(s1.getDiametro()+s2.getDiametro());
            seres.remove(indice_s2);
        }
        else if(s2.getDiametro() > s1.getDiametro()){
            s2.setDiametro(s1.getDiametro()+s2.getDiametro());
            seres.remove(indice_s1);
        }
        else if (s1.getCor() == Color.BLUE && s2.getCor() == Color.YELLOW){
            s1.setCor(Color.GREEN);
            s1.setDiametro(s1.getDiametro()+s2.getDiametro());
            seres.remove(indice_s2);
        }
        else if(s2.getCor() == Color.BLUE && s1.getCor() == Color.YELLOW){
            s2.setCor(Color.GREEN);
            s2.setDiametro(s1.getDiametro()+s2.getDiametro());
            seres.remove(indice_s1);
        }
        else if (s2.getCor() == Color.GREEN){
            s2.setDiametro(s1.getDiametro()+s2.getDiametro());
            seres.remove(indice_s1);
        }
        else {
            s1.setDiametro(s1.getDiametro()+s2.getDiametro());
            seres.remove(indice_s2);
        }
    }

    private void game_over(int alt) {

        HallDaFama hallDaFama = new HallDaFama();
        Boolean deveEntrarParaOHallDaFama = hallDaFama.deveEntrarParaOHallDaFama(pontuacao);

        String mensagem = "";
        String som = "MortePlayer.wav";
        String titulo = "Game Over";
        int optionPane = JOptionPane.PLAIN_MESSAGE; //tipo de painel que vai surgir

        switch (alt){
            case 0: //morte devido a colisao
                if (deveEntrarParaOHallDaFama){
                    mensagem = "Você perdeu!\nMas entrou para o Hall da Fama\n" + pontuacao + " pontos";
                }else{
                    mensagem = "Você perdeu!\nPontuação: " + pontuacao;
                }


                break;
            case 1: ///morte por fome
                if (deveEntrarParaOHallDaFama){
                    mensagem = "Morreu de Fome!\nMas entrou para o Hall da Fama";
                }else{
                    mensagem = "Moreu de fome!\nPontuação: " + pontuacao;
                }

                break;
            case 2: ///Venceu todos os niveis
                if (deveEntrarParaOHallDaFama){
                    mensagem = "Você venceu!\nE ainda entrou para o Hall da Fama\nPontuação: " + pontuacao;
                }else{
                    mensagem = "Você venceu!\nnPontuação: " + pontuacao;
                }
                som = "VoceVenceu.wav";
                break;

            default:
                break;
        }

        Sounds.playSound(som);

        if (deveEntrarParaOHallDaFama){
            String nome = "";

            while (nome == null || nome.equals("")) {
                nome = JOptionPane.showInputDialog(mensagem,
                        "Digite seu nome");
                if (nome == null || nome.equals("")) {
                    JOptionPane.showMessageDialog(null,"Digite o seu nome");
                }
            }
            hallDaFama.novaEntrada(new EntradaHallDaFama(nome, pontuacao));
        }else{
            JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);
        }



        controleDeTela.gameOver();
//        System.exit(0);
    }


    private void gerar_seres(int n){
        int seres_gerados = 0;
        while(seres_gerados < n){
            Ser ser = new Ser(this);
            if(
                    Math.sqrt(
                            Math.pow(ser.getPosicao()[0]-racional.getPosicao()[0],2) +
                            Math.pow(ser.getPosicao()[1]-racional.getPosicao()[1],2)
                    ) > 200
            ){
                seres.add(ser);
                seres_gerados++;
            }
        }
    }


}

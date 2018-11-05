import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Ser extends JComponent {
    private Color cor;
    private int x;
    private int y;
    private int direcao;
    private int diametro = 15;
    private final Territorio territorio;
    private static final Random gerador_aleatorio = new Random();
    private int em_fissao = 0;

    Ser(Territorio territorio){
        int c = gerador_aleatorio.nextInt(2);
        if(c == 0){
            cor = Color.BLUE;
        }
        else {
            cor = Color.YELLOW;
        }

        x = gerador_aleatorio.nextInt(territorio.largura);
        y = gerador_aleatorio.nextInt(territorio.altura);

        direcao = gerador_aleatorio.nextInt(4);
        this.territorio = territorio;
    }

    Ser(Territorio territorio, int x, int y, int diametro, Color cor, int direcao) {
        this.territorio = territorio;
        this.x = x;
        this.y = y;
        this.diametro = diametro;
        this.cor = cor;
        this.direcao = direcao;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(cor);
        g2d.fillOval(x, y, diametro, diametro);

        if (em_fissao != 0 && em_fissao < diametro){
            g2d.setColor(Color.RED);
            g2d.fillOval(x+diametro/2-em_fissao/2, y+diametro/2-em_fissao/2, em_fissao, em_fissao);

            this.em_fissao += 1;

        }
    }

    void mover() {
        switch (direcao){
            case 0:
                if(x<0) {
                    direcao = 1;
                }
                else if(y<0){
                    direcao = 3;
                }
                else{
                    x -= 1; y -= 1;
                }
                break;
            case 1:
                if(x>territorio.largura-diametro-16) {
                    direcao = 0;
                }
                else if(y<0){
                    direcao = 2;
                }
                else {
                    x += 1; y -= 1;
                }

                break;
            case 2:
                if(x>territorio.largura-diametro-16) {
                    direcao = 3;
                }
                else if(y>territorio.altura-diametro-37){
                    direcao = 1;
                }
                else {
                    x += 1; y += 1;
                }

                break;
            case 3:
                if(x<0) {
                    direcao = 2;
                }
                else if(y>territorio.altura-diametro-37){
                    direcao = 0;
                }
                else {
                    x -= 1; y += 1;
                }
                break;
        }
    }

    Rectangle getRetangulo() {
        return new Rectangle(x,y,diametro,diametro);
    }

    void setCor(Color cor) {
        this.cor = cor;
    }

    Color getCor() {
        return cor;
    }

    int getDiametro() {
        return diametro;
    }

    void setDiametro(int diametro) {
        int diametro_maximo = 100;
        if(diametro > diametro_maximo){
            this.diametro = diametro_maximo;
        }
        else {
            this.diametro = diametro;
        }
    }

    int[] getPosicao(){
        return new int[]{x,y};
    }

    void iniciar_fissao() {
        em_fissao = 1;
    }

    int get_em_fissao() {
        return em_fissao;
    }

    void set_em_fissao(int em_fissao) {
        this.em_fissao = em_fissao;
    }

    int getDirecao() {
        return direcao;
    }


}

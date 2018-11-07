import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Racional extends JComponent {

    private final Color cor;
    private int x;
    private int y;
    private final Territorio territorio;
    private final int tamanho = 30;

    private boolean pressed_left = false;
    private boolean pressed_right = false;
    private boolean pressed_up = false;
    private boolean pressed_down = false;

    Image image;
    private int angle;


    Racional(Territorio territorio) throws IOException {
        this.cor = Color.MAGENTA;
        this.territorio = territorio;
        this.x = territorio.largura/2 -tamanho;
        this.y = territorio.altura/2 -tamanho;

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("src/imagens/personagem_menina.png"));
            image = bufferedImage.getScaledInstance(tamanho, tamanho,  java.awt.Image.SCALE_SMOOTH ) ;
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }

    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(cor);
//        g2d.fillRect(x, y, tamanho, tamanho);

        
        g2d.drawImage(image,x,y,null);

    }

    void keyPressed(KeyEvent e){

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pressed_left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pressed_right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pressed_up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pressed_down = true;
        }

    }

    void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pressed_left = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pressed_right = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            pressed_up = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pressed_down = false;
        }

    }

    void mover(){
        int velocidade = 3;
        if(pressed_left && x>0) x -= velocidade;
        if(pressed_right && x<territorio.largura-tamanho-16) x += velocidade;
        if(pressed_up && y>0) y -= velocidade;
        if(pressed_down && y<territorio.altura-tamanho-37) y += velocidade;
    }

    Rectangle getRetangulo() {
        return new Rectangle(x,y,tamanho,tamanho);
    }

    int[] getPosicao(){
        return new int[]{x+tamanho/2,y+tamanho/2};
    }
}



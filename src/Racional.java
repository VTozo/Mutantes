import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform;

import java.lang.Math;

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
    private double angle;


    Racional(Territorio territorio) throws IOException {

        Configuracao configuracao = new Configuracao();
        configuracao = new Configuracao();
        try {
            configuracao = configuracao.abrir();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("personagem: " + configuracao.personagem);

        String imgPathName = "src/imagens/personagem_" + configuracao.personagem + ".png";

        this.cor = Color.MAGENTA;
        this.territorio = territorio;
        this.x = territorio.largura/2 - tamanho;
        this.y = territorio.altura/2 - tamanho;


        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imgPathName));
            image = bufferedImage.getScaledInstance(tamanho, tamanho,  java.awt.Image.SCALE_SMOOTH ) ;
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }

    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;

        g2d.setColor(cor);
//        g2d.fillRect(x, y, tamanho, tamanho);

        //Make a backup so that we can reset our graphics object after using it.
        AffineTransform backup = g2d.getTransform();
        //rx is the x coordinate for rotation, ry is the y coordinate for rotation, and angle
        //is the angle to rotate the image. If you want to rotate around the center of an image,
        //use the image's center x and y coordinates for rx and ry.
        double radAngle = Math.toRadians(angle);
        AffineTransform a = AffineTransform.getRotateInstance(radAngle, x + tamanho/2, y+tamanho/2);
        //Set our Graphics2D object to the transform
        g2d.setTransform(a);
        //Draw our image like normal
        g2d.drawImage(image, x, y, null);
        //Reset our graphics object so we can draw with it again.
        g2d.setTransform(backup);

//        g2d.drawImage(image,x,y,null);

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

//        calculo do angulo, considerando:


        if (pressed_right){
            if (pressed_down) angle = 135 - 90;
            else if(pressed_up) angle = 45 - 90;
            else angle = 90 - 90;
        }else if(pressed_left){
            if (pressed_down) angle = -135 - 90;
            else if(pressed_up) angle = -45 - 90;
            else angle = -90 - 90;
        }else {
            if (pressed_down) angle = 180 - 90;
            else if (pressed_up) angle = 0 - 90;
        }



    }

    Rectangle getRetangulo() {
        return new Rectangle(x,y,tamanho,tamanho);
    }

    int[] getPosicao(){
        return new int[]{x+tamanho/2,y+tamanho/2};
    }
}



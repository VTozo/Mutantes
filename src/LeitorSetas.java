import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class LeitorSetas implements KeyListener{

//    trocado para usar o Leitor como SingleTon
    private static final LeitorSetas INSTANCE = new LeitorSetas();

    private Racional racional = null;

    public static LeitorSetas getInstance(){
        return INSTANCE;
    }

    LeitorSetas() {
        System.out.println("leitor de setas criado");
        System.out.println("racional (leitorSetas): " + racional);
    }

    public Racional getRacional(Racional racional){
        return this.racional;
    }

    public void setRacional(Racional racional){
        this.racional = racional;
    }

    public void keyTyped(KeyEvent e){
    }

    public void keyPressed(KeyEvent e){
//        System.out.println("LeitorSetas event: " + e);
        racional.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        racional.keyReleased(e);
    }
}

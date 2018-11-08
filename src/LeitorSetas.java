import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class LeitorSetas implements KeyListener {

    private final Racional racional;

    LeitorSetas(Racional racional) {
        this.racional = racional;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        racional.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        racional.keyReleased(e);
    }
}

package gamecore;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean firePressed = false;

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.leftPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.rightPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.firePressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                this.leftPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                this.rightPressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                this.firePressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 27) {
            System.exit(0);
        }
    }

    public boolean getFirePressed() {
        return firePressed;
    }

    public boolean getLeftPressed() {
        return leftPressed;
    }

    public boolean getRightPressed() {
        return rightPressed;
    }
}
